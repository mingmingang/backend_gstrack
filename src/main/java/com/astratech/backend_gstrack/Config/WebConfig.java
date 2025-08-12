package com.astratech.backend_gstrack.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Ambil nilai path direktori upload dari application.properties
    @Value("${file.upload-dir}")
    private String uploadDir;
//
//    @PostConstruct
//    public void init()
//    {
//        System.out.println("Upload directory: "+uploadDir);
//    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Normalisasi path direktori upload
        String resolvedPath = Paths.get(uploadDir).toAbsolutePath().normalize().toString();

        // Daftarkan resource handler
        // Ini memberi tahu Spring Boot:
        // Setiap request ke URL yang dimulai dengan "/uploads/"...
        // ...harus menyajikan file dari direktori fisik di "file:/[path absolut ke direktori upload Anda]/"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + resolvedPath + "/");
    }
}