package com.secured.auth.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenApiCustomizer customOpenApi() {
        return openApi -> openApi.info(new Info()
            .title("User API")
            .version("1.0.0")
            .description("API for managing users")
            .contact(new Contact().name("API Support")
            		.email("support@example.com")));
    }
    
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer").bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement()
            		.addList("bearerAuth"))
            .info(new Info().title("User API")
            		.version("1.0.0")
            		.description("API for managing users"));
    }
}
