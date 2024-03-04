package chireia.pc.registerapptest.repository;

import chireia.pc.registerapptest.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}