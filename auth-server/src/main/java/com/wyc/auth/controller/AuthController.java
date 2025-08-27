package com.wyc.auth.controller;

import com.wyc.auth.utils.AuthenticationUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: wuychs
 * @Date: 2025/8/26
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @RequestMapping("/token")
    public String accessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return AuthenticationUtil.generateAccessToken(authentication, jwtEncoder);
    }
}
