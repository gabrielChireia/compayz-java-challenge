package chireia.pc.registerapptest.service;

import chireia.pc.registerapptest.dto.ClientDto;
import chireia.pc.registerapptest.entity.Client;
import chireia.pc.registerapptest.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client create(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        return clientRepository.save(client);
    }


}
