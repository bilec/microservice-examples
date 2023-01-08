package com.example.micronautmicroservice.hello;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/api")
public class HelloController {

    @Get(uri = "/hello", produces = MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from micronaut";
    }
}
