package chireia.pc.registerapptest.controller;

import chireia.pc.registerapptest.dto.ClientDto;
import chireia.pc.registerapptest.entity.Client;
import chireia.pc.registerapptest.service.ClientService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ClientDto> getAll() {
        List<Client> clientList = clientService.getAll();
        return clientList.stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .toList();
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto clientDto) {
        Client savedClient = clientService.create(clientDto);
        ClientDto savedClientDto = modelMapper.map(savedClient, ClientDto.class);
        return new ResponseEntity<>(savedClientDto, HttpStatus.CREATED);
    }
}
