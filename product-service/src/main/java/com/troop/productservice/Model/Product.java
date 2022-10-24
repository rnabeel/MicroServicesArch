package com.troop.productservice.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Products")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {

    @Id
    private String Id;
    private String name;
    private int productCode;
    private String productCompany;
    private int price;

}
