package com.troop.orderservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class OrderLineItemsDto {
    private Long id;
    private String productCode;
    private BigDecimal price;
    private Integer quantity;
}
