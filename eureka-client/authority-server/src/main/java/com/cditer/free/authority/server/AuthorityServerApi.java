package com.cditer.free.authority.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-08-16 12:41
 * @comment
 */

@RestController
@RequestMapping("/api/v1/server/")
public class AuthorityServerApi {
    @Value("${spring.application.name:}")
    private String serverName;

    @GetMapping("name")
    public String name(){
        return serverName;
    }

    @GetMapping("ping")
    public String ping(){
        return "pong";
    }

    @PostMapping("pong")
    public String pong(String ping){
        return String.format("ping:%s",ping);
    }
}
