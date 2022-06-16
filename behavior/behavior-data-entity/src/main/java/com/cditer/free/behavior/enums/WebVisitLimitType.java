package com.cditer.free.behavior.enums;

import com.cditer.free.core.enums.BaseEnum;

import java.util.Arrays;
import java.util.Optional;

public enum WebVisitLimitType implements BaseEnum<Integer> {
    TEN_MINUTE("tenMinute", 10 * 60), // 十分钟
    HALF_HOUR("halfHour", 30 * 60), // 半小时
    ONE_HOUR("oneHour", 60 * 60), // 一小时
    TWO_HOUR("twoHour", 2 * 60 * 60), // 两小时
    ONE_DAY("oneDay", 24 * 60 * 60); // 一天

    private String text;

    private Integer value;

    WebVisitLimitType(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public static WebVisitLimitType parseByText(String text){
        Optional<WebVisitLimitType> first = Arrays.stream(WebVisitLimitType.values()).filter(item -> item.getText().equals(text)).findFirst();
        if(first.isPresent()){
            return first.get();
        }
        return null;
    }
}
