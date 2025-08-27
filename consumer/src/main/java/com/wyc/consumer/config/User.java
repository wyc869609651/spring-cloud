package com.wyc.consumer.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuychs
 * @Date: 2024/12/19
 */
@ConfigurationProperties(prefix = "user")
@Data
@Slf4j
public class User implements InitializingBean, DisposableBean {
    private String name;
    private int age;


    public void afterPropertiesSet() throws Exception {
        log.info("[afterPropertiesSet] user name : {}, age : {}", name, age);
    }

    public void destroy() throws Exception {
        log.info("[destroy] user name : {}, age : {}", name, age);
    }

}
