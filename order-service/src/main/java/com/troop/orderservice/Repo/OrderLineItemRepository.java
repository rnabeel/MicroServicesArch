package com.troop.orderservice.Repo;

import com.troop.orderservice.Model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItems, Long> {
}
