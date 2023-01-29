package com.troop.inventoryservice.Service;

import com.troop.inventoryservice.Model.Inventory;
import com.troop.inventoryservice.Model.RedisInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@CacheConfig(cacheNames = {"InventoryServiceImpl"})
public class InventoryServiceImpl implements Serializable {

    @Autowired
    MongoTemplate mongoTemplate;

    public Map<String, Boolean> checkInventory(Map<String, Integer> checkQuantityPerCode) {
        Map<String, Boolean> checkQuantityPerCodeResponse = new HashMap<>();

        for (Map.Entry entry : checkQuantityPerCode.entrySet()) {
            String productCode = entry.getKey().toString();
            int quantity = (int) entry.getValue();
            RedisInventory inventoryList = checkInventoryPerProduct(productCode);
            if (inventoryList != null) {
                checkQuantityPerCodeResponse.put((String) entry.getKey(), true);
            } else if (inventoryList == null) {
                checkQuantityPerCodeResponse.put((String) entry.getKey(), false);
            }
        }
        return checkQuantityPerCodeResponse;
    }

    @Cacheable(key = "#productCode")
    public RedisInventory checkInventoryPerProduct(String productCode){
        Map<String, Boolean> checkQuantityPerCodeResponse = new HashMap<>();
        Aggregation ags = newAggregation(
                match(Criteria.where("productCode").is(productCode)),
//                match(Criteria.where("quantity")
//                        .gte(quantity)),
                project("productCode", "quantity")
        );
        RedisInventory resonse =  mongoTemplate.aggregate(ags, "Inventory_TB", RedisInventory.class).getUniqueMappedResult();
        System.out.println(resonse);
        return resonse;
    }

}

