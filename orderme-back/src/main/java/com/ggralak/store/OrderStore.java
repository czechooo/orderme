package com.ggralak.store;

import com.ggralak.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderStore {
    private static List<Order> orders = new ArrayList<>();

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static List<Order> getOrders() {
        return orders;
    }

}
