package com.cditer.free.develop.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-08-16 11:08
 * @comment
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DevelopServerApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DevelopServerApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DevelopServerApp.class);
    }
}
