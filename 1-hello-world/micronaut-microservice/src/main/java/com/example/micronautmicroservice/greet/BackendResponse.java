package com.example.micronautmicroservice.greet;

import lombok.Data;

@Data
public class BackendResponse {
    private String greeting;
    private String time;
    private String ip;
}