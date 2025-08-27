package com.wyc.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Param;

/**
 * @Author: wuychs
 * @Date: 2024/12/23
 */
@FeignClient(name = "spring-cloud-provider")
public interface OrderService {

    @PostMapping("/order/add")
    boolean addOrder(@RequestParam("user") String user, @RequestParam("commodityCode") String commodityCode, @RequestParam("money") long money);
}
