package com.example.springbootmicroservice.greet;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@ConfigurationProperties(prefix="greet")
@Data
public class GreetController {
    private RestTemplate restTemplate = new RestTemplate();
    private String saying;
    private String backendServiceHost;
    private int backendServicePort;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = "text/plain")
    public String greeting(){
        String backendServiceUrl = String.format("http://%s:%d/backend?greeting={greeting}", backendServiceHost, backendServicePort);
        BackendResponse response = restTemplate.getForObject(backendServiceUrl, BackendResponse.class, saying);
        return response.getGreeting() + " at host: " + response.getIp();
    }
}
