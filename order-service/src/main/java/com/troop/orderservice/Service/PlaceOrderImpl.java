package com.troop.orderservice.Service;

import com.troop.orderservice.Dto.OrderRequest;
import com.troop.orderservice.Repo.PlaceOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PlaceOrderImpl implements PlaceOrder {
    public static String INVENTORY_API_URL = "http://localhost:8083/api/inventory";
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private PlaceOrderRepo placeOrderRepo;

    @Override
    public Map placeOrder(OrderRequest orderRequest) {

        Map checkQuantityPerCode = new HashMap<>();

        orderRequest.getOrderItemsList().forEach(x -> checkQuantityPerCode.put(x.getProductCode(), x.getQuantity()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map> entity = new HttpEntity<>(checkQuantityPerCode, headers);
        Map responseData = restTemplate.exchange(INVENTORY_API_URL, HttpMethod.POST, entity, Map.class).getBody();

        assert responseData != null;
        return placeOrderRepo.orderRepo(responseData,orderRequest);
    }
}
