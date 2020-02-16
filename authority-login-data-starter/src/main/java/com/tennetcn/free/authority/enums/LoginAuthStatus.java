package com.tennetcn.free.authority.enums;

import com.tennetcn.free.core.enums.BaseEnum;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-16 09:42
 * @comment
 */

public enum LoginAuthStatus implements BaseEnum<String> {
    VALID("valid","有效"),
    INVALID("invalid","无效");


    private String key;

    private String value;

    LoginAuthStatus(String key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }
}
