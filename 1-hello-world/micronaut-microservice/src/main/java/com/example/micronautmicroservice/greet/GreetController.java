package com.example.micronautmicroservice.greet;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.uri.UriBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

@Controller("/api")
public class GreetController {
    private final HttpClient httpClient;
    private final GreetConfiguration greetConfiguration;

    public GreetController(@NonNull GreetConfiguration greetConfiguration) throws MalformedURLException {
        this.greetConfiguration = greetConfiguration;
        String backendServiceUrl = String.format("http://%s:%s", greetConfiguration.getBackendServiceHost(), greetConfiguration.getBackendServicePort());
        this.httpClient = HttpClient.create(new URL(backendServiceUrl));
    }

    @Get(uri = "/greeting", produces = MediaType.TEXT_PLAIN)
    public String greeting() {
        URI uri = UriBuilder.of("/backend")
                .queryParam("greeting", greetConfiguration.getSaying())
                .build();
        HttpRequest<BackendResponse> httpRequest = HttpRequest.GET(uri);

        BackendResponse backendResponse = httpClient.toBlocking().retrieve(httpRequest, BackendResponse.class);
        return backendResponse.getGreeting() + " at host: " + backendResponse.getIp();
    }
}