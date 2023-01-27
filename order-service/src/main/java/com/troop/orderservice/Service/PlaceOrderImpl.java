package com.troop.orderservice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.troop.orderservice.Dto.OrderRequest;
import com.troop.orderservice.InterService.WebClientBroker;
import com.troop.orderservice.Repo.PlaceOrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class PlaceOrderImpl implements PlaceOrder {
    public static String INVENTORY_API_URL = "/inventory/isInStock";
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private PlaceOrderRepo placeOrderRepo;

    private WebClientBroker webClientBroker;
    @Override
    public Map placeOrder(OrderRequest orderRequest) throws JsonProcessingException {

        Map checkQuantityPerCode = new HashMap<>();

        orderRequest.getOrderItemsList().forEach(x -> checkQuantityPerCode.put(x.getProductCode(), x.getQuantity()));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(checkQuantityPerCode);
        Map responseData = webClientBroker.postRequest(INVENTORY_API_URL,json);

        assert responseData != null;
        return placeOrderRepo.orderRepo(responseData,orderRequest);
    }
}
