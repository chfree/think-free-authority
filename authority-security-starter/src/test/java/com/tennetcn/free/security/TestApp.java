package com.tennetcn.free.security;

import com.tennetcn.free.security.core.JwtHelper;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-23 12:05
 * @comment
 */

public class TestApp {
    @Test
    public void test() {
        Map<String,Object> claims = new HashMap<>();
        claims.put("a","chenghuan");
        String token = JwtHelper.instance().createJwt("1001",claims,1000*60*10);

        System.out.println(token);
    }

    @Test
    public void test1(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhIjoiY2hlbmdodWFuIiwibmJmIjoxNTY2NTU0MjIzLCJleHAiOjE1NjY1NTQ4MjMsImlhdCI6MTU2NjU1NDIyM30.ChsYI0WN8h4bL12e-agmYj6IBpUFA9oMV6WKIqAZNsA";
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJhIjoiY2hlbmdodWFuIiwibmJmIjoxNTY2NTU0MTQxLCJleHAiOjE1NjY1NTQ3NDEsImlhdCI6MTU2NjU1NDE0MX0.BsCR803hn894u0H8Q9Y_PdD34CeHqsPdjfBTIfkJHUY";
        Claims claims = JwtHelper.instance().parseJWT(token);

        String id = claims.getId();
        String a = claims.get("a").toString();

        System.out.println(id);
        System.out.println(a);
        System.out.println(claims.getExpiration());

        System.out.println(JwtHelper.instance().isTokenExpired(claims.getExpiration()));
    }

}
