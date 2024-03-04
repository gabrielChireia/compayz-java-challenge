package chireia.pc.registerapptest.repository;

import chireia.pc.registerapptest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}