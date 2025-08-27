package com.wyc.consumer.controller;

import com.wyc.consumer.config.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuychs
 * @Date: 2024/12/18
 */
@RestController
@Slf4j
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${user.name:default}")
    private String userName;

    @Value("${user.age:0}")
    private int userAge;

    @Autowired
    private User user;


    @PostConstruct
    public void init() {
        log.info("[init] user name : {}, age : {}", userName, userAge);
    }

    @PreDestroy
    public void destroy() {
        log.info("[destroy] user name : {}, age : {}", userName, userAge);
    }

    @GetMapping("/userName")
    public String getUserName(){
        return userName;
    }

    @GetMapping("/userAge")
    public int getUserAge(){
        return userAge;
    }

    @GetMapping("/user")
    public User getUser(){
        return user;
    }

}
