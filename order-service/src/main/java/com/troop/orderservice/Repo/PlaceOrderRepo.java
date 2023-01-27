package com.troop.orderservice.Repo;

import com.troop.orderservice.Dto.OrderLineItemsDto;
import com.troop.orderservice.Dto.OrderRequest;
import com.troop.orderservice.Model.Order;
import com.troop.orderservice.Model.OrderLineItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;

import static com.troop.orderservice.cfg.OrderConstants.AVAILABLE;
import static com.troop.orderservice.cfg.OrderConstants.NOT_AVAILABLE;

@Repository
public class PlaceOrderRepo {

    @Autowired
    private OrderRepository orderRepository;

    public Map orderRepo(Map<String, Integer> responseData, OrderRequest orderRequest, String CustomerId) {
        Map responseMessage = new HashMap<>();
        List<OrderLineItems> ListOfOrderLineItems = new ArrayList<>();
        Order order = new Order();


        for (Map.Entry entry : responseData.entrySet()) {
            orderRequest.getOrderItemsList().forEach(x -> {
                String orderProductCode = x.getProductCode();
                if (Objects.equals(entry.getKey().toString(), orderProductCode) && Objects.equals(entry.getValue().toString(), "true")) {
                    OrderLineItems orderLineItems = mapToDto(x, orderProductCode);
                    ListOfOrderLineItems.add(orderLineItems);
                    responseMessage.put(entry.getKey().toString(), AVAILABLE);
                } else {
                    if (!responseMessage.containsKey(entry.getKey())) {
                        responseMessage.put(entry.getKey().toString(), NOT_AVAILABLE);
                    }
                }
            });
        }

        for (OrderLineItems orderLineItem : ListOfOrderLineItems) {
            orderLineItem.setOrder(order);
        }

        order.setOrderLineItems(ListOfOrderLineItems);
        order.setCustcode(orderRequest.getCustomerId());
        orderRepository.save(order);
        return responseMessage;
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
