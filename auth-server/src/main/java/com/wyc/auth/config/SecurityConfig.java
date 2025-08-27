package com.wyc.auth.config;

import com.wyc.auth.utils.AuthenticationUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author: wuychs
 * @Date: 2025/8/22
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 用于认证的Spring Security过滤器链。
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, JwtEncoder jwtEncoder)
            throws Exception {
        http.authorizeHttpRequests((authorize) ->
                    authorize
                            .requestMatchers("/assets/**","/webjars/**","/actuator/**","/oauth2/**","/auth/**").permitAll()
                            .anyRequest().authenticated()
            )
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(formLoginConfigurer -> formLoginConfigurer.loginProcessingUrl("/auth/login")
                    .successHandler(new CookieAuthenticationSuccessHandler(jwtEncoder)))
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置内存用户
     */
    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * 验证成功后，将设置access_token cookie的处理器
     */
    private static class CookieAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

        private final JwtEncoder jwtEncoder;

        public CookieAuthenticationSuccessHandler(JwtEncoder jwtEncoder) {
            this.jwtEncoder = jwtEncoder;
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            String tokenValue = AuthenticationUtil.generateAccessToken(authentication, jwtEncoder);
            Cookie accessTokenCookie = new Cookie("access_token", tokenValue);
            accessTokenCookie.setPath("/");
            accessTokenCookie.setMaxAge(7200);
            response.addCookie(accessTokenCookie);
        }
    }
}
