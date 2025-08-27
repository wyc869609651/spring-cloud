package com.wyc.consumer.controller;

import com.wyc.consumer.service.BusinessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("/purchase")
    public String purchase(String user, String commodityCode, long count) {
        return businessService.purchase(user, commodityCode, count);
    }
}
