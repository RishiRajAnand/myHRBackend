package com.erx.microservice.patientmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
@Profile("swagger")
public class SwaggerConfig {

    private final ErxProperties erxProperties;

    public SwaggerConfig(ErxProperties erxProperties) {
        this.erxProperties = erxProperties;
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(erxProperties.getSwagger().getBasePackage()))
                .paths(regex(erxProperties.getSwagger().getDefaultIncludePattern()))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                erxProperties.getSwagger().getTitle(),
                erxProperties.getSwagger().getDescription(),
                erxProperties.getSwagger().getVersion(),
                "Terms of service",
                new Contact(erxProperties.getSwagger().getContactName(), erxProperties.getSwagger().getContactUrl(), erxProperties.getSwagger().getContactEmail()),
                erxProperties.getSwagger().getLicense(),
                erxProperties.getSwagger().getLicenseUrl());
        return apiInfo;
    }
}
