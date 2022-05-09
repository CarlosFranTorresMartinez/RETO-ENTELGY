package com.entelgy.retotecnico.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ApiConfig {

    @Value("${uri}")
    private String uri;

    @Bean
    public RestTemplate restTemplate() {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(uri);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(defaultUriBuilderFactory);
        return restTemplate;
    }
}
