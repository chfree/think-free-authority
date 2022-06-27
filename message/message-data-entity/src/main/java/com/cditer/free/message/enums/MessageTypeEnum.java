package com.cditer.free.message.enums;

import com.cditer.free.core.enums.BaseEnum;

public enum MessageTypeEnum implements BaseEnum<String> {

    /**
     * systemManage:系统通知
     * systemBus：系统业务，比如超访问控制，比如流控
     * userBus：用户在业务操作时产生的相关消息
     */
    SYSTEM_MANAGE("系统管理","systemManage"),
    SYSTEM_BUS("系统业务","systemBus"),
    USER_BUS("用户业务", "userBus");

    private String text;

    private String value;

    MessageTypeEnum(String text, String value) {
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
