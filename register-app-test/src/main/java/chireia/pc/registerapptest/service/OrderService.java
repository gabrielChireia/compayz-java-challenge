package chireia.pc.registerapptest.service;

import chireia.pc.registerapptest.dto.OrderDto;
import chireia.pc.registerapptest.dto.OrderProductDto;
import chireia.pc.registerapptest.entity.Order;
import chireia.pc.registerapptest.entity.OrderProduct;
import chireia.pc.registerapptest.entity.Product;
import chireia.pc.registerapptest.exception.InvalidRequestException;
import chireia.pc.registerapptest.exception.ResourceNotFoundException;
import chireia.pc.registerapptest.repository.OrderProductRepository;
import chireia.pc.registerapptest.repository.OrderRepository;
import chireia.pc.registerapptest.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, ModelMapper modelMapper,
                        OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.orderProductRepository = orderProductRepository;
    }

    @Transactional
    public Order create(OrderDto orderDto) throws InvalidRequestException, ResourceNotFoundException {
        Order order = modelMapper.map(orderDto, Order.class);
        List<Product> products = getAllProductsFromId(orderDto);
        //Verifica se a quantidade de cada produto no pedido está disponivel para ser retirada da tabela de produtos
        validateProductQuantities(orderDto, products);

        order.setTotalPrice(0.0);
        order.setProducts(null);

        orderRepository.save(order);
        setOrderProductsAndTotalPriceAndSaveProducts(order, orderDto, products);

        return orderRepository.findById(order.getId()).orElseThrow(ResourceNotFoundException::new);
    }

    private List<Product> getAllProductsFromId(OrderDto orderDto) {
        List<Long> productIds = orderDto.getProducts().stream()
                .map(orderProductDto -> orderProductDto.getProduct().getId())
                .toList();

        return productRepository.findAllById(productIds);
    }

    private void setOrderProductsAndTotalPriceAndSaveProducts(Order order, OrderDto orderDto, List<Product> products) throws ResourceNotFoundException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        double totalPrice = 0;

        //Cria o link de todos os produtos do pedido na tabela order_product e calcula o valor total do pedido
        for (OrderProductDto orderProductDto : orderDto.getProducts()) {
            int productQuantityOnOrder = orderProductDto.getQuantity();
            Product product = getProductFromList(products, orderProductDto);

            product.setQuantity(product.getQuantity() - productQuantityOnOrder);
            OrderProduct orderProduct = new OrderProduct(product, order, productQuantityOnOrder);
            orderProducts.add(orderProduct);
            totalPrice += product.getPrice() * productQuantityOnOrder;
        }

        saveAllTables(products, orderProducts, totalPrice, order);
    }

    private void validateProductQuantities(OrderDto orderDto, List<Product> products) throws ResourceNotFoundException, InvalidRequestException {
        for (OrderProductDto orderProductDto : orderDto.getProducts()) {
            int productQuantityOnOrder = orderProductDto.getQuantity();
            Product product = getProductFromList(products, orderProductDto);

            if (productQuantityOnOrder > product.getQuantity()) {
                throw new InvalidRequestException("Not enough product in stock");
            }
        }
    }

    private Product getProductFromList(List<Product> products, OrderProductDto orderProductDto) throws ResourceNotFoundException {
        return products.stream()
                .filter(p -> p.getId().equals(orderProductDto.getProduct().getId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    private void saveAllTables(List<Product> products, List<OrderProduct> orderProducts, Double totalPrice, Order order) {
        productRepository.saveAll(products);
        List<OrderProduct> savedOrderProducts = orderProductRepository.saveAll(orderProducts);

        order.setProducts(savedOrderProducts);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
    }
}
