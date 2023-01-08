package com.example.micronautmicroservice.greet;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

@ConfigurationProperties("greet")
@Data
public class GreetConfiguration {
    private String saying;
    private String backendServiceHost;
    private String backendServicePort;
}
