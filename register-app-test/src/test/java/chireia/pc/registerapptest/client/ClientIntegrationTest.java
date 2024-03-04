package chireia.pc.registerapptest.client;

import chireia.pc.registerapptest.entity.Client;
import chireia.pc.registerapptest.repository.ClientRepository;
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
class ClientIntegrationTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void shouldSavePost() {
        Client expectedClientObject1 = new Client(null, "Gabriel Chireia", "065.569.847-96", "(65)94654-5619", "gchireia@gmail.com");
        Client expectedClientObject2 = new Client(null, "John Doe", "123.456.789-00", "(123)456-7890", "john.doe@example.com");
        Client expectedClientObject3 = new Client(null, "Jane Smith", "987.654.321-00", "(555)555-5555", "jane.smith@example.com");
        Client expectedClientObject4 = new Client(null, "Bob Johnson", "111.222.333-44", "(111)111-1111", "bob.johnson@example.com");

        List<Client> clientsListToSave = new ArrayList<>();
        clientsListToSave.add(expectedClientObject1);
        clientsListToSave.add(expectedClientObject2);
        clientsListToSave.add(expectedClientObject3);
        clientsListToSave.add(expectedClientObject4);

        List<Client> savedClientsList = clientRepository.saveAll(clientsListToSave);

        assertThat(savedClientsList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(clientsListToSave);
    }
}