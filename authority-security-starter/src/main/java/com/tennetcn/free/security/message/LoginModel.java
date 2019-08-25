package com.tennetcn.free.security.message;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-25 00:09
 * @comment
 */

@Data
public class LoginModel {
    private String id;

    private String name;

    private String account;

    private Map<String,Object> arguments=new HashMap<>();

    public void put(String key,Object object){
        this.arguments.put(key,object);
    }

    public Object get(String key){
        return this.arguments.get(key);
    }

    @JsonAnyGetter
    public Map<String,Object> getArguments() {
        return this.arguments;
    }
}
