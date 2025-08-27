package com.wyc.provider.controller;

import com.wyc.provider.service.StorageService;

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
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/down/{commodityCode}/{count}")
    public boolean storageDown(@PathVariable String commodityCode, @PathVariable long count) {
        return storageService.storageDown(commodityCode, count);
    }
}
