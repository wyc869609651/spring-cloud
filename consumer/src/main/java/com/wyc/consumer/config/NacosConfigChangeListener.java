package com.wyc.consumer.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.AbstractListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 监听器方式监听Nacos配置变动
 * @Author: wuychs
 * @Date: 2024/12/19
 */
@Slf4j
@Component
public class NacosConfigChangeListener implements ApplicationRunner {

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String dataId = "spring-cloud-consumer";
        String group = "DEFAULT_GROUP";
        nacosConfigManager.getConfigService().addListener(dataId, group, new AbstractListener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("监听到配置变动：{}", configInfo);
            }
        });
    }
}
