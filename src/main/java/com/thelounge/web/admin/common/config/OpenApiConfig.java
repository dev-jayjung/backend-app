package com.thelounge.web.admin.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@Profile("!prd")
@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("THE LOUNGE MEMBERS API")
                        .description("API 문서입니다.")
                        .version("v1"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    // @Bean
    // GroupedOpenApi apiAll(OpenApiCustomizer openApiCustomizer) {
    // return GroupedOpenApi.builder()
    // .group("00.전체")
    // .pathsToMatch("/api/**")
    // .addOpenApiCustomizer(openApiCustomizer)
    // .build();
    // }
}