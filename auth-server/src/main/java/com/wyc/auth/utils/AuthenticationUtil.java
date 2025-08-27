package com.wyc.auth.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author: wuychs
 * @Date: 2025/8/27
 */
public class AuthenticationUtil {


    /**
     * 根据认证对象生产accessToken
     * @param authentication
     * @param jwtEncoder
     * @return
     */
    public static String  generateAccessToken(Authentication authentication, JwtEncoder jwtEncoder) {
        User user = (User) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        JwsHeader header = JwsHeader.with(SignatureAlgorithm.RS256).build();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("auth-server")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(7200))
                .subject(user.getUsername())
                .claim("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")))
                .id(UUID.randomUUID().toString())
                .build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(header, claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }
}
