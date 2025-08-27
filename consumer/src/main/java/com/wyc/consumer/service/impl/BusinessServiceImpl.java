package com.wyc.consumer.service.impl;

import com.wyc.consumer.service.AccountService;
import com.wyc.consumer.service.BusinessService;
import com.wyc.consumer.service.OrderService;
import com.wyc.consumer.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "purchase", rollbackFor = Exception.class)
    public String purchase(String user, String commodityCode, long count) {
        log.info("用户{}购买商品{}共{}件", user, commodityCode, count);
        if (!storageService.storageDown(commodityCode, count)) {
            throw new RuntimeException("购买失败：库存不足！");
        }
        log.info("库存扣减成功");
        long money = count * 100;
        if (!accountService.detach(user, money)) {
            throw new RuntimeException("购买失败：用户余额不足！");
        }
        log.info("扣除用户余额成功！");
        if (orderService.addOrder(user, commodityCode, money)) {
            log.info("下单成功");
            return "购买成功";
        }
        throw new RuntimeException("购买失败：用户余额不足！");
    }
}
