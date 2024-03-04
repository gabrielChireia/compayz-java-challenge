package chireia.pc.registerapptest.order;

import chireia.pc.registerapptest.entity.Order;
import chireia.pc.registerapptest.entity.OrderProduct;
import chireia.pc.registerapptest.entity.Product;
import chireia.pc.registerapptest.repository.OrderRepository;
import chireia.pc.registerapptest.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        products.add(new Product(null, "Arroz", 50, 10.00));
        products.add(new Product(null, "Feijão", 30, 8.50));
        products.add(new Product(null, "Açúcar", 20, 5.00));
        products.add(new Product(null, "Óleo de Soja", 15, 12.00));

        productRepository.saveAll(products);
    }

    @Test
    void shouldSaveOrder() {
        Order order = new Order();
        order.setClientName("John Doe");
        order.setIssueDate(LocalDate.now());
        order = orderRepository.save(order);

        List<OrderProduct> orderItems = new ArrayList<>();
        orderItems.add(new OrderProduct(null, products.get(0), order, 3));
        orderItems.add(new OrderProduct(null, products.get(1), order, 2));
        orderItems.add(new OrderProduct(null, products.get(2), order, 1));
        orderItems.add(new OrderProduct(null, products.get(3), order, 4));
        order.setProducts(orderItems);

        Order savedOrder = orderRepository.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getIssueDate()).isEqualTo(order.getIssueDate());
        assertThat(savedOrder.getClientName()).isEqualTo(order.getClientName());
        assertThat(savedOrder.getProducts()).hasSize(order.getProducts().size());
        assertThat(savedOrder.getProducts().get(0).getProduct().getId())
                .isEqualTo(order.getProducts().get(0).getProduct().getId());
        assertThat(savedOrder.getProducts().get(0).getQuantity())
                .isEqualTo(order.getProducts().get(0).getQuantity());
    }
}
