package com.cditer.free.message.service;

import com.cditer.free.message.entity.viewmodel.MessageSendView;

/**
 * 消息发送工场
 */
public interface IMessageSendFactory {
    boolean sendMessage(String tempId, MessageSendView messageSendView);
}
