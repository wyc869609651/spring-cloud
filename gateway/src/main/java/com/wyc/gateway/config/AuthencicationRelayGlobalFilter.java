package com.wyc.gateway.config;

import com.alibaba.fastjson.JSON;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import reactor.core.publisher.Mono;

/**
 * 认证中继全局过滤器。将认证信息放到专属的请求头中，便于后续服务间调用。
 * @Author: wuychs
 * @Date: 2025/8/29
 */
@Component
public class AuthencicationRelayGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return exchange.getPrincipal()
                .filter(principal -> principal instanceof Authentication)
                .cast(Authentication.class)
                .map(authentication -> {
                    Map<String, String> userInfo = new HashMap<>();
                    userInfo.put("principal", authentication.getName());
                    userInfo.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")));
                    return Base64.getEncoder().encodeToString(JSON.toJSONBytes(userInfo));
                })
                .map(userInfo -> exchange.mutate().request(r->r.header("X-Principal-Token", userInfo)).build())
                .defaultIfEmpty(exchange)
                .flatMap(chain::filter);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
