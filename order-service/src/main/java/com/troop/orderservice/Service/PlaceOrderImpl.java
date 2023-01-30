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
        orderRequest.getOrderItemsList().forEach(x -> checkQuantityPerCode.put(x.getProductCode(), x.getQuantity()));
        Map dataMap = new HashMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(checkQuantityPerCode);
        Mono<Map> response = webClientBroker.processData(INVENTORY_API_URL, json);

        return response
                .flatMap(data -> {
                    System.out.println("fff");
                    // handle the response from the other microservice
                    dataMap.putAll(data);
                    // return a Mono that completes asynchronously
                    return Mono.just(dataMap);
                })
                .toFuture()
                .thenApply(data -> placeOrderRepo.orderRepo(data, orderRequest, orderRequest.getCustomerId()));
    }


//    The main thread invokes the placeOrder method.
//
//    The processData method is called which returns a Mono.
//
//    The main thread continues executing and subscribes to the Mono returned by processData.
//
//    The flatMap operator transforms the Mono by calling the data -> Mono.just(dataMap) lambda.
//
//    The main thread calls toFuture() on the transformed Mono, which returns a CompletableFuture.
//
//    The main thread calls thenApply on the CompletableFuture which invokes the placeOrderRepo.orderRepo method asynchronously.
//
//            Meanwhile, the Mono returned by processData is still executing and handling the response from the other microservice in the background.
//
//    Once the response from the other microservice is processed, it triggers the completion of the Mono and the dataMap is updated.
//
//    The thenApply method is invoked with the updated dataMap.
//
//    The main thread continues executing and does not block, even though the CompletableFuture is executing asynchronously in the background.
//
//    This way, the main thread remains free to perform other tasks while the response from the other microservice is being processed.
}
