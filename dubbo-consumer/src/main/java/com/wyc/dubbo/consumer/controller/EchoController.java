package com.wyc.dubbo.consumer.controller;

import com.wyc.dubbo.api.IEchoService;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wuychs
 * @Date: 2024/12/25
 */
@RestController
public class EchoController {

    @DubboReference(interfaceClass = IEchoService.class)
    private IEchoService echoService;

    @GetMapping("/dubbo/echo/{message}")
    public String echo(@PathVariable String message) {
        return echoService.echo(message);
    }
}
