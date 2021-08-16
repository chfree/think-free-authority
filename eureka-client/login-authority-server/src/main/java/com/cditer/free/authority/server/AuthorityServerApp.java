package com.cditer.free.authority.server;

import org.springframework.beans.factory.annotation.Value;
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
public class AuthorityServerApp  extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityServerApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AuthorityServerApp.class);
    }
}
