package com.troop.orderservice.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Order_TB")
@RequiredArgsConstructor
@Data
public class Order {

    private String orderNumber;
    private List<OrderLineItems> orderLineItems;

}
