package com.troop.orderservice.Model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderLineItems {

    private String productCode;
    private BigDecimal price;
    private Integer quantity;

}
