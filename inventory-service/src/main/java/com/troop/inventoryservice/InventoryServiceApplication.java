package com.troop.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableCaching
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
