package com.troop.orderservice.Controller;

import com.troop.orderservice.Dto.OrderRequest;
import com.troop.orderservice.Service.PlaceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placeOrder")
public class OrderController {
    @Autowired
    PlaceOrder placeOrder;

    @PostMapping()
    public ResponseEntity placeOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(placeOrder.placeOrder(orderRequest), HttpStatus.OK);
    }
}
