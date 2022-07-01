package com.cditer.free.message.entity.viewmodel;

import com.cditer.free.message.entity.model.MessageInfo;
import lombok.Data;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 16:26:01
 * @comment     消息表
 */

@Data
public class MessageInfoView extends MessageInfo {
    private String addUserName;
}