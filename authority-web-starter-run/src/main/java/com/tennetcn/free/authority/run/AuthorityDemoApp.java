package com.tennetcn.free.authority.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:08
 * @comment
 */

@SpringBootApplication
@EnableSwagger2
public class AuthorityDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityDemoApp.class,args);
    }
}
