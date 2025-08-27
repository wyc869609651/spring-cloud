package com.wyc.provider.service.impl;

import com.wyc.provider.mapper.AccountMapper;
import com.wyc.provider.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuychs
 * @Date: 2025/1/2
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public boolean detach(String user, long money) {
        try {
            return accountMapper.detach(user, money);
        } catch (Exception e) {
            log.error("账户扣减失败！", e);
            return false;
        }
    }
}
