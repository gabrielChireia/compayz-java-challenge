package chireia.pc.registerapptest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto implements Serializable {
    private LocalDate issueDate;
    private String description;
    private List<OrderProductDto> products;
    private double totalPrice;
    private String clientName;
}