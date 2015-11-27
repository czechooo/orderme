package com.ggralak.integration;

import com.ggralak.model.Order;
import com.ggralak.model.OrderItem;

import java.util.List;

public class OrderItemsAggregator {

    public Order add(List<OrderItem> items) {
        Order order = new Order();

        order.setItems(items);

        return order;
    }
}
