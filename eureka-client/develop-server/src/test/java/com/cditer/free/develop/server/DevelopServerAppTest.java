package com.cditer.free.develop.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-09-01 22:38
 * @comment
 */

@SpringBootApplication
public class DevelopServerAppTest extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DevelopServerAppTest.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DevelopServerAppTest.class);
    }
}
