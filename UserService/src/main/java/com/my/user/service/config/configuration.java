package com.my.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class configuration {

    @Bean
    @LoadBalanced // To load balance between service instances running at different ports.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
