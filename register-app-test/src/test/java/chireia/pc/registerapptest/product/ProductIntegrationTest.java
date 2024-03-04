package chireia.pc.registerapptest.product;

import chireia.pc.registerapptest.entity.Product;
import chireia.pc.registerapptest.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductIntegrationTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveProducts() {
        Product expectedProductObject1 = new Product(null, "Arroz", 50, 10.00);
        Product expectedProductObject2 = new Product(null, "Feijão", 30, 8.50);
        Product expectedProductObject3 = new Product(null, "Açúcar", 20, 5.00);
        Product expectedProductObject4 = new Product(null, "Óleo de Soja", 15, 12.00);

        List<Product> productsListToSave = new ArrayList<>();
        productsListToSave.add(expectedProductObject1);
        productsListToSave.add(expectedProductObject2);
        productsListToSave.add(expectedProductObject3);
        productsListToSave.add(expectedProductObject4);

        List<Product> savedProductsList = productRepository.saveAll(productsListToSave);

        assertThat(savedProductsList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(productsListToSave);
    }
}
