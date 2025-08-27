package com.wyc.provider.service;

import org.springframework.stereotype.Service;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
public interface OrderService {
    boolean addOrder(String user, String commodityCode, long money);
}
