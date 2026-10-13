package com.thelounge.web.admin.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.thelounge.web.admin.common.interceptor.MenuAccessLogInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AdminWebConfig implements WebMvcConfigurer {

    private final MenuAccessLogInterceptor menuAccessLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(menuAccessLogInterceptor);
    }

}