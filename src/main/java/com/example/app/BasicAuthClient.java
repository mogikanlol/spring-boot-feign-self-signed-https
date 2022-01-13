package com.example.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name ="basic-auth-client", url = "https://localhost:8443", configuration = BasicAuthClientConfiguration.class)
public interface BasicAuthClient {

    @GetMapping("/api/auth/basic/hello")
    String getHello(@RequestHeader("Authorization") String basicAuthHeader);
}

