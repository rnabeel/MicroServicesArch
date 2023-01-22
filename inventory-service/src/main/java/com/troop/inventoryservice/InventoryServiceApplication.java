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

//    @Bean
//    public CommandLineRunner loadData(MongoTemplate mongoTemplate) {
//        return args -> {
//            Inventory inventory = new Inventory();
//            inventory.setProductCode("Iphone-11");
//            inventory.setQuantity(1000);
//
//            Inventory inventory1 = new Inventory();
//            inventory1.setQuantity(1000);
//            inventory1.setProductCode("Iphone-13");
//
//            mongoTemplate.save(inventory1);
//            mongoTemplate.save(inventory);
//
//        };
//    }
}
