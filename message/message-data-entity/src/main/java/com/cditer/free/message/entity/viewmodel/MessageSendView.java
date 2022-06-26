package com.cditer.free.message.entity.viewmodel;

import com.cditer.free.message.entity.model.MessageReceive;
import lombok.Data;

import java.util.List;

@Data
public class MessageSendView<T> {
    /**
     * freemark模板格式化的对象
     */
    private T data;

    /**
     * 接收模式
     * fitLink
     * 写接收人的时候，可以是角色、部门、组等非具体人员，是一种可持续接收
     *  比如给管理员发送，张三当前不是管理员，但消息已经发送了，在将张三添加为管理员，此时张三还是可以收到消息
     *
     * directLink
     * 写接收人的时候，直接就是用户id，
     * 与fitLink不同在于消息发送就确定人员
     */
    private String receiveMode;

    /**
     * 接收对象
     */
    private List<MessageReceive> messageReceives;
}
