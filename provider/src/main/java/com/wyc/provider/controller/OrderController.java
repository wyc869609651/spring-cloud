package com.wyc.provider.controller;

import com.wyc.provider.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/add")
    public boolean addOrder(String user, String commodityCode, long money) {
        return orderService.addOrder(user, commodityCode, money);
    }
}
