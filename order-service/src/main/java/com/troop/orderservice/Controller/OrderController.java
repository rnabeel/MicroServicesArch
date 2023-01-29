package com.troop.orderservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.troop.orderservice.Dto.OrderRequest;
import com.troop.orderservice.Service.PlaceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.troop.orderservice.cfg.OrderURI.BASE_URI;
import static com.troop.orderservice.cfg.OrderURI.PLACEORDER_URI;

@RestController
@RequestMapping(BASE_URI)
public class OrderController {
    @Autowired
    PlaceOrder placeOrder;

    @GetMapping(PLACEORDER_URI)
    public CompletableFuture<ResponseEntity<?>> placeOrder(@RequestBody OrderRequest orderRequest) throws JsonProcessingException, ExecutionException, InterruptedException {
         return placeOrder.placeOrder(orderRequest).thenApply(ResponseEntity::ok);
    }
}
