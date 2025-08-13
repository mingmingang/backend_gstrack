package com.astratech.backend_gstrack;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@EnableScheduling
@SpringBootApplication
public class BackendGstrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendGstrackApplication.class, args);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(50)); // max per file
        factory.setMaxRequestSize(DataSize.ofMegabytes(50)); // total request
        return factory.createMultipartConfig();
    }
}
