package com.cditer.free.login.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-09-25 17:54
 * @comment
 */

@EnableDiscoveryClient
@SpringBootApplication
public class LoginServerApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LoginServerApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LoginServerApp.class);
    }
}
