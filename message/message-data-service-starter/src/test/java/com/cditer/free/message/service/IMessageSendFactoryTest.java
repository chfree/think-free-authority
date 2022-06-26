package com.cditer.free.message.service;

import com.cditer.free.message.BaseTest;
import com.cditer.free.message.entity.model.MessageInfo;
import com.cditer.free.message.entity.model.MessageReceive;
import com.cditer.free.message.entity.viewmodel.MessageSendView;
import com.cditer.free.message.enums.ReceiveTypeEnum;
import com.cditer.free.message.testmodel.TestUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class IMessageSendFactoryTest extends BaseTest {

    @Autowired
    IMessageSendFactory messageSendFactory;

    @Test
    public void sendMessageTest(){
        MessageSendView<TestUser> messageSendView = new MessageSendView<>();
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setTitle("这是一个测试消息");
        messageSendView.setTempName("test");

        TestUser testUser = new TestUser();
        testUser.setName("CH");
        messageSendView.setData(testUser);

        MessageReceive messageReceive = new MessageReceive();
        messageReceive.setReceiveId("chfree");
        messageReceive.setReceiveType(ReceiveTypeEnum.USER.getValue());

        messageSendView.setMessageReceives(Arrays.asList(messageReceive));

        messageSendFactory.sendMessage(messageSendView);
    }
}