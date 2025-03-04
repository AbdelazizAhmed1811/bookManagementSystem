package com.example.First_sprint_boot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("API Documentation")
//                        .version("1.0")
//                        .description("API documentation for First Sprint Boot"))
//                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
//                .components(new io.swagger.v3.oas.models.Components()
//                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
//                                .name("BearerAuth")
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")));
//    }


    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("My REST API")
                        .description("Some custom description of API.")
//                        .version("1.0").contact(new Contact().name("Sallo Szrajbman")
//                                .email( "www.baeldung.com").url("salloszraj@gmail.com"))
                        .license(new License().name("License of API")
                                .url("API license URL")));
    }
}
