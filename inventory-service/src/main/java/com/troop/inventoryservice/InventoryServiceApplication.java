package com.troop.inventoryservice;

import com.troop.inventoryservice.Model.Inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
//
//    @Bean
//    public CommandLineRunner loadData(MongoTemplate mongoTemplate) {
//        return args -> {
//            Inventory inventory = new Inventory();
//            inventory.setProductCode("Iphone_13");
//            inventory.setQuantity(10);
//
//            Inventory inventory1 = new Inventory();
//            inventory1.setQuantity(15);
//            inventory1.setProductCode("iphone_13_red");
//
//            mongoTemplate.save(inventory1);
//            mongoTemplate.save(inventory);
//
//        };
//    }
}
