package com.wyc.consumer.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@Service
@Slf4j
public class TimeService {

    @SentinelResource(value = "TimeService#getTime", fallback = "getTimeFallback")
    public long getTime() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return System.currentTimeMillis();
    }

    private long getTimeFallback(Throwable t) {
        if (BlockException.isBlockException(t)){
            log.error("getTime方法发生了限流！");
            return 0;
        }
        log.error("getTime方法发生了异常！", t);
        return -1;
    }
}
