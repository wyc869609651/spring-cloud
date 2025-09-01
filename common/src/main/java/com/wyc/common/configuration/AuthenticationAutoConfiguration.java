package com.wyc.common.configuration;

import com.wyc.common.filter.AuthenticationFilter;
import com.wyc.common.filter.AuthenticationRequestInterceptor;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import feign.RequestInterceptor;

/**
 * @Author: wuychs
 * @Date: 2025/8/29
 */
@AutoConfiguration
public class AuthenticationAutoConfiguration {
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuthenticationFilter());
        registration.addUrlPatterns("/*");
        registration.setName("authenticationFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }


    @Configuration
    @ConditionalOnClass(RequestInterceptor.class)
    public static class AuthenticationFeignConfiguration {
        @Bean
        public RequestInterceptor authenticationRequestInterceptor() {
            return new AuthenticationRequestInterceptor();
        }
    }

}
