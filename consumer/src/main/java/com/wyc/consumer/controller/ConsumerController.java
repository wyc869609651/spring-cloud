package com.wyc.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: wuychs
 * @Date: 2024/12/23
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/call/echo/{message}")
    public String callEcho(@PathVariable String message) {
        return restTemplate.getForObject("http://spring-cloud-provider/echo/" + message, String.class);
    }
}
