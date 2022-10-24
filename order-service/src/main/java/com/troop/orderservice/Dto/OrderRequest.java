package com.troop.orderservice.Dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderRequest {
private List<OrderLineItemsDto> orderItemsList;

}
