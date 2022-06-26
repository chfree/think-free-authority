package com.cditer.free.message.testmodel;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TestUser {
    private String name;

    private int  age;

    private Date birthday;

    private List<String> hobbys;
}
