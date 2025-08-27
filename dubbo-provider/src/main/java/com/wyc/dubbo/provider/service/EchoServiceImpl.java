package com.wyc.dubbo.provider.service;

import com.wyc.dubbo.api.IEchoService;

import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author: wuychs
 * @Date: 2024/12/25
 */
@DubboService
public class EchoServiceImpl implements IEchoService {
    @Override
    public String echo(String message) {
        return "[Dubbo Echo]: "+message;
    }
}
