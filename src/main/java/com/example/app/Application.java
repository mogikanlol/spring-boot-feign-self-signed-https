package com.example.app;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(HttpsClient httpsClient,
                                    BasicAuthClient basicAuthClient) {
        return args -> {
            System.out.println(httpsClient.getHello());

//            String basicAuthHeader = "Basic "
//                    + Base64.getEncoder().encodeToString("test:123456".getBytes(StandardCharsets.UTF_8));
//
//            System.out.println(basicAuthClient.getHello(basicAuthHeader));
        };
    }
}
