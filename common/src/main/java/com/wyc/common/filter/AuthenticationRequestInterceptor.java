package com.wyc.common.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * OpenFeign的认证拦截器，用于传递上下文用户的认证信息
 * @Author: wuychs
 * @Date: 2025/8/29
 */
@Slf4j
public class AuthenticationRequestInterceptor implements RequestInterceptor {

    private static final String PRINCIPAL_HEADER = "X-Principal-Token";

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            template.header(PRINCIPAL_HEADER, authentication.getCredentials().toString());
        }else {
            log.debug("No authentication found for feign url:{}", template.feignTarget().url());
        }
    }
}
