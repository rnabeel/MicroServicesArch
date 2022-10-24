package com.troop.orderservice.Service;
import com.troop.orderservice.Dto.OrderRequest;

import java.util.Map;

public interface PlaceOrder {
    public Map placeOrder(OrderRequest orderRequest);
}
