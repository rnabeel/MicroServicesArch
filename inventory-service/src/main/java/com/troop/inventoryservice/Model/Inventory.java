package com.troop.inventoryservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Inventory_TB")
@Data
public class Inventory {

    @Id
    private String Id;
    private String productCode;
    private Integer quantity;

}
