package com.tennetcn.free.develop.data.enums;

import com.tennetcn.free.core.enums.BaseEnum;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-06 15:44
 * @comment
 */

public enum CodeTmpPub implements BaseEnum<String> {
    PRI("私有","01"),
    PUB("公开","02");


    private String text;
    private String value;

    CodeTmpPub(String text, String value) {
        this.text = text;
        this.value = value;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
