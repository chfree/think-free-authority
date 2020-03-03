package com.tennetcn.free.authority.run;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.swagger.annotations.EnableThinkSwagger;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.SimpleDateFormat;

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
