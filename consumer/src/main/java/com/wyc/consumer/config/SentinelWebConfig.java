package com.wyc.consumer.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@Configuration
public class SentinelWebConfig {

    @Bean
    public BlockExceptionHandler blockExceptionHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setStatus(429);
            httpServletResponse.getWriter().write("Too many requests: " + e.getClass().getSimpleName());
        };
    }
}
