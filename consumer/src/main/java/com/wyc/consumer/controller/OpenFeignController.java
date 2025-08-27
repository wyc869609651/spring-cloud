package com.wyc.consumer.controller;

import com.wyc.consumer.service.EchoService;
import com.wyc.consumer.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wuychs
 * @Date: 2024/12/23
 */
@RestController
public class OpenFeignController {

    @Autowired
    private EchoService echoService;


    @GetMapping("/feign/echo/{message}")
    public String feignEcho(@PathVariable String message) {
        return echoService.echo(message);
    }
}
