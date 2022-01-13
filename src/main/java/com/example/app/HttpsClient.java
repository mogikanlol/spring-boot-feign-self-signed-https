package com.example.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name ="https-client", url = "https://localhost:8443", configuration = HttpsClientConfiguration.class)
public interface HttpsClient {

    @GetMapping("/api/auth/cert/hello")
    String getHello();
}
