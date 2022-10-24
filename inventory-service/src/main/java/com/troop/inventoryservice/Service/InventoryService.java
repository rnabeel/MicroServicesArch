package com.troop.inventoryservice.Service;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    public Map<String, Boolean> checkInventory(Map<String,Integer> checkQuantityPerCode);
}
