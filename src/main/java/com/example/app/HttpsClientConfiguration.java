package com.example.app;

import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

@Slf4j
public class HttpsClientConfiguration {

    @Bean
    public Client feignClient() {
        return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
    }

    private SSLSocketFactory getSSLSocketFactory() {
        try {

            SSLContext sslContext = SSLContexts.custom()
                    .setKeyStoreType("pkcs12")
                    .loadKeyMaterial(
                            new ClassPathResource("local/clientkeystore.p12").getFile(),
                            "changeit".toCharArray(),
                            "changeit".toCharArray()
                    )
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
