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
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.troop.orderservice.cfg.OrderURI.INVENTORY_API_URL;

@Service
@AllArgsConstructor
public class PlaceOrderImpl implements PlaceOrder {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private PlaceOrderRepo placeOrderRepo;

    private WebClientBroker webClientBroker;

    @Override
    public CompletableFuture<Map> placeOrder(OrderRequest orderRequest) throws JsonProcessingException, ExecutionException, InterruptedException {

        Map checkQuantityPerCode = new HashMap<>();
        Map dataMap = new HashMap<>();
        orderRequest.getOrderItemsList().forEach(x -> checkQuantityPerCode.put(x.getProductCode(), x.getQuantity()));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(checkQuantityPerCode);
        Mono<Map> response = webClientBroker.postRequest(INVENTORY_API_URL, json);

        CompletableFuture<Map> future = response.toFuture();
//        future.thenAccept(data-> {
//            dataMap.putAll(data);
//        });
        dataMap.putAll(future.get());

        assert dataMap.size()!=0;
        return CompletableFuture.completedFuture(placeOrderRepo.orderRepo(dataMap,orderRequest,orderRequest.getCustomerId()));
    }
    }
