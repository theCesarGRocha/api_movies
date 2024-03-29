package com.api.challenge.context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
//@Configuration // allow create beans in app context
@EnableSwagger2 // Allow swager 2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.challenge.controller"))//scan all packages in this source
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API",
                "API REST Movies.",
                "v1",
                "Code Challenge",
                new Contact("Cesar G. ROCHA", "www.example.com", "cesargrocha92@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}