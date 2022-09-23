package com.cditer.free.stateflow.data.enums;

import com.cditer.free.core.enums.BaseEnum;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/23 13:15
 * @comment
 */

public enum TaskStatusEnum implements BaseEnum<String> {

    TODO("待办", "todo"),
    DOING("在办", "doing"),
    DONE("已办", "done");

    private String text;

    private String value;

    TaskStatusEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getValue() {
        return value;
    }
}
