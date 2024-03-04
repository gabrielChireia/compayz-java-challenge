package chireia.pc.registerapptest.controller;

import chireia.pc.registerapptest.dto.OrderDto;
import chireia.pc.registerapptest.exception.InvalidRequestException;
import chireia.pc.registerapptest.exception.ResourceNotFoundException;
import chireia.pc.registerapptest.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody OrderDto orderDto) throws InvalidRequestException, ResourceNotFoundException {
        orderService.create(orderDto);
        return new ResponseEntity<>("Pedido criado", HttpStatus.CREATED);
    }
}
