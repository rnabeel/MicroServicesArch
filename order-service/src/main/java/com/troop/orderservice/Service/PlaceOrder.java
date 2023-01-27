package com.troop.orderservice.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.troop.orderservice.Dto.OrderRequest;

import java.util.Map;

public interface PlaceOrder {
    public Map placeOrder(OrderRequest orderRequest) throws JsonProcessingException;
}
