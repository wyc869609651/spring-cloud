package com.wyc.gateway.config;

import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrors;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * access_token cookie到Authentication对象的转换器
 * @Author: wuychs
 * @Date: 2025/8/26
 */
public class CookieBearerAuthenticationConverter implements ServerAuthenticationConverter {

    private String cookieName = "access_token";

    public CookieBearerAuthenticationConverter() {
    }

    public CookieBearerAuthenticationConverter(String cookieName) {
        this.cookieName = cookieName;
    }


    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.fromCallable(() -> token(exchange.getRequest())).map((token) -> {
            if (token.isEmpty()) {
                BearerTokenError error = invalidTokenError();
                throw new OAuth2AuthenticationException(error);
            }
            return new BearerTokenAuthenticationToken(token);
        });
    }

    private static BearerTokenError invalidTokenError() {
        return BearerTokenErrors.invalidToken("Bearer token is malformed");
    }

    private String token(ServerHttpRequest request) {
        HttpCookie accessToken = request.getCookies().getFirst(cookieName);
        if (accessToken != null) {
            return accessToken.getValue();
        }
        return null;
    }
}
