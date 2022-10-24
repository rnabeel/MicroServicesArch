package com.troop.inventoryservice.Service;

import com.troop.inventoryservice.Model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Map<String, Boolean> checkInventory(Map<String, Integer> checkQuantityPerCode) {
        Map<String, Boolean> checkQuantityPerCodeResponse = new HashMap<>();

        for (Map.Entry entry : checkQuantityPerCode.entrySet()) {

            Aggregation ags = newAggregation(
                    match(Criteria.where("productCode").is(entry.getKey())),
                    match(Criteria.where("quantity")
                            .gte(entry.getValue())),
                    project("productCode", "quantity")
            );
            Inventory inventoryList = mongoTemplate.aggregate(ags, "Inventory_TB", Inventory.class).getUniqueMappedResult();

            if (inventoryList != null) {
                checkQuantityPerCodeResponse.put((String) entry.getKey(), true);
            } else if (inventoryList == null) {
                checkQuantityPerCodeResponse.put((String) entry.getKey(), false);
            }
        };
        return checkQuantityPerCodeResponse;
    }

}

