package com.wyc.provider.controller;

import com.wyc.provider.service.AccountService;
import com.wyc.provider.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/detach/{user}/{money}")
    public boolean detach(@PathVariable String user, @PathVariable long money) {
        return accountService.detach(user, money);
    }
}
