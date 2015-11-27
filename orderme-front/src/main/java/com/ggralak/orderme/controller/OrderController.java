package com.ggralak.orderme.controller;

import com.ggralak.integration.OrderGateway;
import com.ggralak.model.Order;
import com.ggralak.model.OrderItem;
import com.ggralak.orderme.dto.CreateOrderDTO;
import com.ggralak.orderme.dto.GenericResponse;
import com.ggralak.store.OrderStore;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private static List<Order> orders = new ArrayList<>();

    @Autowired
    private OrderGateway orderGateway;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String showCreateOrder() {

        return "order/create";
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public GenericResponse createOrder(@RequestBody CreateOrderDTO createOrderDTO) {

        orderGateway.createOrder(createOrderDTO.getText());

        GenericResponse response = new GenericResponse();
        response.setMessage("created " + createOrderDTO.getText());

        return response;
    }

    @RequestMapping(value = "/order/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Order> orderList() {
        return OrderStore.getOrders();
    }

}
