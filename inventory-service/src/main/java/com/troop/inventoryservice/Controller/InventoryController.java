package com.troop.inventoryservice.Controller;

import com.troop.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/isInStock")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> isInStock(@RequestBody Map<String,Integer> checkQuantityPerCode) {
       return inventoryService.checkInventory(checkQuantityPerCode);
    }

}
