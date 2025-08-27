package com.wyc.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: wuychs
 * @Date: 2024/12/23
 */
@FeignClient(name = "spring-cloud-provider")
public interface AccountService {

    @GetMapping("/account/detach/{user}/{money}")
    boolean detach(@PathVariable("user") String user, @PathVariable("money") long money);
}
