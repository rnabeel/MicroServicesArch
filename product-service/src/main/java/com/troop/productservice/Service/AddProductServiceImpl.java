package com.troop.productservice.Service;

import com.troop.productservice.Dto.ProductRequest;
import com.troop.productservice.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddProductServiceImpl implements AddProductService {

    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public String addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productCompany(productRequest.getProductCompany())
                .price(productRequest.getPrice())
                .name(productRequest.getName())
                .productCode(productRequest.getProductCode())
                .build();

        mongoTemplate.insert(product);
        return  "product with id "+product.getProductCode()+" added successfully!";
    }
}
