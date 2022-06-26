package com.cditer.free.message.entity.viewmodel;

import com.cditer.free.message.entity.model.MessageInfo;
import com.cditer.free.message.entity.model.MessageReceive;
import lombok.Data;

import java.util.List;

@Data
public class MessageSendView<T> {
    /**
     * freemark模板格式化的对象
     */
    private T data;

    private String tempName;

    private MessageInfo messageInfo;

    /**
     * 接收对象
     */
    private List<MessageReceive> messageReceives;
}
