package com.wyc.common.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 认证过滤器，用于从请求头中获取认证信息，构造Authentication对象，并设置到SecurityContextHolder中。
 * @Author: wuychs
 * @Date: 2025/8/29
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String PRINCIPAL_HEADER = "X-Principal-Token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(PRINCIPAL_HEADER);
        if (StringUtils.isBlank(token)){
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token内容
        byte[] decode = Base64.getDecoder().decode(token);
        JSONObject jsonObject = JSON.parseObject(new String(decode));
        String principal = jsonObject.getString("principal");
        String authoritiesText = jsonObject.getString("authorities");
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(StringUtils.split(authoritiesText, " "));
        // 构造Authentication对象，并设置到SecurityContextHolder中
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, token, authorities);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        filterChain.doFilter(request, response);
    }
}
