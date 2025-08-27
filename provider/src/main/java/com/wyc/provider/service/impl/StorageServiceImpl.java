package com.wyc.provider.service.impl;

import com.wyc.provider.service.StorageService;
import com.wyc.provider.mapper.StorageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    public boolean storageDown(String commodityCode, long count) {
        try {
            return storageMapper.storageDown(commodityCode, count);
        } catch (Exception e) {
            log.error("库存扣减失败！", e);
            return false;
        }
    }
}
