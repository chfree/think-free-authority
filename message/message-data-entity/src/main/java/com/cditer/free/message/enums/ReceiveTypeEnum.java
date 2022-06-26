package com.cditer.free.message.enums;

import com.cditer.free.core.enums.BaseEnum;

public enum ReceiveTypeEnum implements BaseEnum<String> {
    USER("用户", "user"),
    ROLE("角色", "role"),
    USER_GROUP("用户组", "userGroup"),
    MESSAGE_GROUP("消息组", "messageGroup");

    private String text;
    private String value;

    ReceiveTypeEnum(String text,String value){
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
