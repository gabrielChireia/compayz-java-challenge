package chireia.pc.registerapptest.controller;

import chireia.pc.registerapptest.dto.ProductDto;
import chireia.pc.registerapptest.entity.Product;
import chireia.pc.registerapptest.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProductDto> getAll() {
        List<Product> productsList = productService.getAll();
        return productsList.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto productDto) {
        Product savedProduct = productService.create(productDto);
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);
        return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
    }
}
