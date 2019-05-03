package com.erx.microservice.patientmanagement.config;


import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final Logger log = LoggerFactory.getLogger(RestTemplateConfig.class);

    private final ErxProperties erxProperties;

    public RestTemplateConfig(ErxProperties erxProperties) {
        this.erxProperties = erxProperties;
    }

    @Bean
    public RestTemplate restTemplate() {

        log.debug("Registering RestTemplate");

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        return restTemplate;
    }
}

