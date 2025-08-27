package com.wyc.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @Author: wuychs
 * @Date: 2025/8/26
 */
public class HeaderSecurityContextRepository implements ServerSecurityContextRepository {

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
            mutate.header("user", authentication.getName());
            mutate.header("role", authentication.getAuthorities().toString());
        }
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return null;
    }
}
