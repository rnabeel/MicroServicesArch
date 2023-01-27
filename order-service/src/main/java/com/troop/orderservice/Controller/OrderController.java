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

@RestController
@RequestMapping("/placeOrder")
public class OrderController {
    @Autowired
    PlaceOrder placeOrder;

    @GetMapping()
    public ResponseEntity<Map> placeOrder(@RequestBody OrderRequest orderRequest) throws JsonProcessingException {
         return new ResponseEntity<>(placeOrder.placeOrder(orderRequest), HttpStatus.OK);
    }
}
