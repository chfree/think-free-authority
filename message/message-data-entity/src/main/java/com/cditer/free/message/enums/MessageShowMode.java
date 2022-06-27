package com.cditer.free.message.enums;

import com.cditer.free.core.enums.BaseEnum;

public enum MessageShowMode implements BaseEnum<String> {
    POPUP_WINDOW("弹窗提示", "popupWindow"),
    MESSAGE_PAGE("消息页面", "messagePage"),;

    private String text;

    private String value;

    MessageShowMode(String text, String value) {
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
