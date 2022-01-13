package com.example.app;

import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

@Slf4j
public class BasicAuthClientConfiguration {

    @Bean
    public Client feignClient() {
        return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
    }

    private SSLSocketFactory getSSLSocketFactory() {
        try {

            SSLContext sslContext = SSLContexts.custom()
                    .setKeyStoreType("pkcs12")
                    .loadTrustMaterial(
                            new ClassPathResource("local/clienttruststore.p12").getFile(),
                            "changeit".toCharArray()
                    )
                    .build();
            return sslContext.getSocketFactory();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }
}
