package chireia.pc.registerapptest.service;

import chireia.pc.registerapptest.dto.ProductDto;
import chireia.pc.registerapptest.entity.Product;
import chireia.pc.registerapptest.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product create(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        return productRepository.save(product);
    }
}
