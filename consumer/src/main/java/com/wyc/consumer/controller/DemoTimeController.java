package com.wyc.consumer.controller;

import com.wyc.consumer.service.impl.TimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@RestController
@RequestMapping("/demoTime")
public class DemoTimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/time")
    public long getTime() {
        return timeService.getTime();
    }
}
