package com.tennetcn.free.authority.apis;

import com.tennetcn.free.web.webapi.FirstApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:05
 * @comment
 */

@RestController
public class LoginApi extends FirstApi {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
