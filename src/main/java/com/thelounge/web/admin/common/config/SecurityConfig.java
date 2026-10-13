package com.thelounge.web.admin.common.config;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;

import com.thelounge.core.web.helper.CookieHelper;
import com.thelounge.web.admin.common.security.CustomAuthenticationEntryPoint;
import com.thelounge.web.admin.common.security.CustomJwtAuthenticationConverter;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomJwtAuthenticationConverter customJwtAuthenticationConverter;
    private final CookieHelper cookieHelper;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // 정적 리소스
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/plugins/**", "/.well-known/**").permitAll()

                        // 스웨거
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        .requestMatchers("/login", "/login/**", "/api/login", "/error").permitAll()

                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .bearerTokenResolver(bearerTokenResolver())
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(customJwtAuthenticationConverter))
                        .authenticationEntryPoint(customAuthenticationEntryPoint))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint));

        return http.build();
    }

    @Bean
    BearerTokenResolver bearerTokenResolver() {
        DefaultBearerTokenResolver resolver = new DefaultBearerTokenResolver();

        // Header뿐만 아니라 Cookie에서도 토큰을 찾도록 설정
        return request -> {
            String uri = request.getRequestURI();

            // /login 페이지, 정적 리소스 등 공개 경로는 토큰 해석을 Skip (무한 리다이렉트 방지)
            if (uri.startsWith("/css") || uri.startsWith("/js") ||
                    uri.startsWith("/plugins") || uri.startsWith("/images") ||
                    uri.equals("/error") || uri.equals("/favicon.ico")) {
                return null;
            }

            // HTTP Header에서 Bearer 토큰 우선 확인 (RestController API 호출용)
            String headerToken = resolver.resolve(request);
            if (headerToken != null) {
                return headerToken;
            }

            return cookieHelper.get("accessToken")
                    .map(Cookie::getValue)
                    .orElse(null);
        };
    }
}