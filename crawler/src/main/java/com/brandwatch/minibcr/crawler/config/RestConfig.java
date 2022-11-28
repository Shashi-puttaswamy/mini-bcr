package com.brandwatch.minibcr.crawler.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(getJacksonConverter());
        messageConverters.add(getStringConverter());
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    private StringHttpMessageConverter getStringConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        return converter;
    }

    private MappingJackson2HttpMessageConverter getJacksonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        return converter;
    }
}
