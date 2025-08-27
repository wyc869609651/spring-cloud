package com.wyc.consumer.service;

/**
 * @Author: wuychs
 * @Date: 2024/12/23
 */
public interface BusinessService {

    String purchase(String user, String commodityCode, long count);
}
