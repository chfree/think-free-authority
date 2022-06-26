package com.cditer.free.message.service.impl;

import com.cditer.free.message.entity.viewmodel.MessageSendView;
import com.cditer.free.message.service.IMessageSendFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageSendFactoryImpl implements IMessageSendFactory {
    @Override
    public boolean sendMessage(String tempId, MessageSendView messageSendView) {
        return false;
    }
}
