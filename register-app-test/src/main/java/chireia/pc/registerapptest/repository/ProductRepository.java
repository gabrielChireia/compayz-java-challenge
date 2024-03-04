package chireia.pc.registerapptest.repository;

import chireia.pc.registerapptest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}