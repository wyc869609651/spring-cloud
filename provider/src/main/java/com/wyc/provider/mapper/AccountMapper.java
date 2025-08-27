package com.wyc.provider.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: wuychs
 * @Date: 2024/12/31
 */
@Mapper
public interface AccountMapper {
    boolean detach(String user, long money);
}
