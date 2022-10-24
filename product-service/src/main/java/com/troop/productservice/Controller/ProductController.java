package com.troop.productservice.Controller;

import com.troop.productservice.Dto.ProductRequest;
import com.troop.productservice.Service.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    AddProductService addProductService;

    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest){
        String reposnseText =  addProductService.addProduct(productRequest);
        return new ResponseEntity<String>(reposnseText,HttpStatus.OK);

    }
}
