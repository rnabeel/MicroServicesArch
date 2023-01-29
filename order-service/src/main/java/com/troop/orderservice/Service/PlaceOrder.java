package com.troop.orderservice.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.troop.orderservice.Dto.OrderRequest;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface PlaceOrder {
    @Async
    public CompletableFuture<Map> placeOrder(OrderRequest orderRequest) throws JsonProcessingException, ExecutionException, InterruptedException;
}
