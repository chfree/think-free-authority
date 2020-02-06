package com.tennetcn.free.develop.enums;

import com.tennetcn.free.core.enums.BaseEnum;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-06 15:44
 * @comment
 */

public enum CodeTmpPub implements BaseEnum<String> {
    PRI("01", "私有"),
    PUB("02", "公开");


    private String key;
    private String value;

    CodeTmpPub(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
