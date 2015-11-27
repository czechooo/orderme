package com.ggralak.integration;

import com.ggralak.model.Order;
import com.ggralak.model.OrderItem;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;

public class OrderSplitter extends AbstractMessageSplitter {


    @Override
    protected List<?> splitMessage(Message<?> message) {
        List<OrderItem> orderItems = new ArrayList<>();

        String orderText = (String)message.getPayload();
        for (String line : orderText.split("\\r?\\n")) {
            orderItems.add(new OrderItem(line));
        }

        return orderItems;
    }
}
