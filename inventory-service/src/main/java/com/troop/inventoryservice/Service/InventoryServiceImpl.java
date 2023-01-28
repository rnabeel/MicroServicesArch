package com.troop.inventoryservice.Service;

import com.troop.inventoryservice.Model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@CacheConfig(cacheNames = {"CurrentDeviceRepository"})
public class InventoryServiceImpl implements InventoryService , Serializable {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Map<String, Boolean> checkInventory(Map<String, Integer> checkQuantityPerCode) {
        Map<String, Boolean> checkQuantityPerCodeResponse = new HashMap<>();

        for (Map.Entry entry : checkQuantityPerCode.entrySet()) {
            String productCode = entry.getKey().toString();
            int quantity = (int) entry.getValue();
            Inventory inventoryList = checkInventoryPerProduct(productCode);
            if (inventoryList != null) {
                checkQuantityPerCodeResponse.put((String) entry.getKey(), true);
            } else if (inventoryList == null) {
                checkQuantityPerCodeResponse.put((String) entry.getKey(), false);
            }
        };
        return checkQuantityPerCodeResponse;
    }

    @Cacheable (key = "#IMEI")
    public Inventory checkInventoryPerProduct(String producCode){
        Map<String, Boolean> checkQuantityPerCodeResponse = new HashMap<>();
        Aggregation ags = newAggregation(
                match(Criteria.where("productCode").is(producCode)),
//                match(Criteria.where("quantity")
//                        .gte(quantity)),
                project("productCode", "quantity")
        );
        Inventory resonse =  mongoTemplate.aggregate(ags, "Inventory_TB", Inventory.class).getUniqueMappedResult();
        System.out.println(resonse);
        return resonse;
    }

}

