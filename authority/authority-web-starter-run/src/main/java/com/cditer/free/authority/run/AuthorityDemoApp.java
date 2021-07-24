package com.cditer.free.authority.run;

import com.cditer.free.swagger.annotations.EnableThinkSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:08
 * @comment
 */

@SpringBootApplication
@EnableThinkSwagger
public class AuthorityDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityDemoApp.class,args);
    }
}
