package com.wyc.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: wuychs
 * @Date: 2024/12/23
 */
@FeignClient(name = "spring-cloud-provider")
public interface StorageService {

    @GetMapping("/storage/down/{user}/{count}")
    boolean storageDown(@PathVariable("user") String user, @PathVariable("count") long count);
}
