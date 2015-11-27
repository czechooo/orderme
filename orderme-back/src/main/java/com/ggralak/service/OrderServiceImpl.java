package com.ggralak.service;

import com.ggralak.model.Order;
import com.ggralak.store.OrderStore;
import org.springframework.stereotype.Service;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Override
    public void processOrder(Order order) {
        OrderStore.addOrder(order);
    }
}
