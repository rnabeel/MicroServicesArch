package com.troop.orderservice.Repo;

import com.troop.orderservice.Dto.OrderLineItemsDto;
import com.troop.orderservice.Dto.OrderRequest;
import com.troop.orderservice.Model.Order;
import com.troop.orderservice.Model.OrderLineItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlaceOrderRepo {
    public static String NOT_AVAILABLE = "is not available right now";
    public static String AVAILABLE = "order is placed";
    @Autowired
    MongoTemplate mongoTemplate;

    public Map orderRepo(Map<String, Integer> responseData, OrderRequest orderRequest){
        Map respMsg = new HashMap<>();
        List<OrderLineItems> respAdd = new ArrayList<>();
        Order order = new Order();

        for (Map.Entry entry : responseData.entrySet()) {
        orderRequest.getOrderItemsList().forEach(x -> {
            String orderProductCode = x.getProductCode();
            if (Objects.equals(entry.getKey().toString(), orderProductCode) && Objects.equals(entry.getValue().toString(), "true")) {
                OrderLineItems orderLineItems = mapToDto(x, orderProductCode);
                respAdd.add(orderLineItems);
                order.setOrderNumber(UUID.randomUUID().toString());
                respMsg.put(entry.getKey().toString(), AVAILABLE);
            }

            else{
                if(!respMsg.containsKey(entry.getKey())) {
                    respMsg.put(entry.getKey().toString(), NOT_AVAILABLE);
                }
            }
        });
    }

        order.setOrderLineItems(respAdd);
        mongoTemplate.insert(order);
        return respMsg;
}

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto, String orderProductCode) {

        OrderLineItems orderLineItems = new OrderLineItems();
        if (orderLineItemsDto.getProductCode().equals(orderProductCode)) {
            orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
            orderLineItems.setProductCode(orderLineItemsDto.getProductCode());
            orderLineItems.setPrice(orderLineItemsDto.getPrice());
        }
        return orderLineItems;
    }
}
