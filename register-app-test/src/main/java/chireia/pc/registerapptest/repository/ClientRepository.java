package chireia.pc.registerapptest.repository;

import chireia.pc.registerapptest.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}