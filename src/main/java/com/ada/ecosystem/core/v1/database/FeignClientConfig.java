package com.ada.ecosystem.core.v1.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;


@Configuration
public class FeignClientConfig {


    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String token = TokenContextHolder.getToken();
                if (token != null) {
                    requestTemplate.header("token", token);
                }
            }
        };
    }
}