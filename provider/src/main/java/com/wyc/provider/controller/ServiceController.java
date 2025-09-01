package com.wyc.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wuychs
 * @Date: 2024/12/23
 */
@RestController
public class ServiceController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            return "[ECHO:"+port+", username:"+authentication.getPrincipal()+"]: " + message;
        }else {
            return "[ECHO:"+port+"]: " + message;
        }
    }
}
