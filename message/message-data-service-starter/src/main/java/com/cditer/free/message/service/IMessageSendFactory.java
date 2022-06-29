package com.cditer.free.message.service;

import com.cditer.free.message.entity.model.MessageInfo;
import com.cditer.free.message.entity.model.MessageReceive;
import com.cditer.free.message.entity.viewmodel.MessageSendView;

import java.util.List;

/**
 * 消息发送工场
 */
public interface IMessageSendFactory {

    <T> boolean sendMessage(MessageSendView<T> messageSendView);

    <T> MessageInfo formatMessageInfo(MessageInfo messageInfo, String tempName,T data);

    boolean sendMessage(MessageInfo messageInfo, List<MessageReceive> messageReceives);

    <T> String buildMessageContent(String tempName,T data);

    <T> String buildMessageTitle(String tempName,T data);

    <T> MessageInfo buildMessageInfo(String tempName, T data);

    List<MessageReceive> buildReceiveByGroupName(String name);

    List<MessageReceive> buildReceiveByGroupName(List<String> names);
}
