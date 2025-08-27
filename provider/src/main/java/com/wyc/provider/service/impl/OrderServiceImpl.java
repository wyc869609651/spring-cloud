package com.wyc.provider.service.impl;

import com.wyc.provider.mapper.OrderMapper;
import com.wyc.provider.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean addOrder(String user, String commodityCode, long money) {
        try {
            return orderMapper.addOrder(user, commodityCode, money);
        } catch (Exception e) {
            log.error("添加订单失败", e);
            return false;
        }
    }
}
