package com.example.springbootmicroservice.greet;

import lombok.Data;

@Data
public class BackendResponse {
    private String greeting;
    private String time;
    private String ip;
}
