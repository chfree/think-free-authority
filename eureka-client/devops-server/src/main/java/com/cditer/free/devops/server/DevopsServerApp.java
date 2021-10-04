package com.cditer.free.devops.server;

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
public class DevopsServerApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DevopsServerApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DevopsServerApp.class);
    }
}
