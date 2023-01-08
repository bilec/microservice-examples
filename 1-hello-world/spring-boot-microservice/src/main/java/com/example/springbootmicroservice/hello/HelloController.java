package com.example.springbootmicroservice.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "text/plain")
    public String hello() {
        return "Hello from spring boot ";
    }
}
