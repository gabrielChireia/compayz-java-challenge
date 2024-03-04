package chireia.pc.registerapptest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderProductDto implements Serializable {
    private Long id;
    private ProductDto product;
    private OrderDto order;
    private int quantity;
}