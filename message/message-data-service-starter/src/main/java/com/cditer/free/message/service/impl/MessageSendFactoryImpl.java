package com.cditer.free.message.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.Pair;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.cditer.free.core.exception.BizException;
import com.cditer.free.core.inceptor.ILoginModelQuery;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.message.entity.model.MessageInfo;
import com.cditer.free.message.entity.model.MessageReceive;
import com.cditer.free.message.entity.viewmodel.MessageSendView;
import com.cditer.free.message.entity.viewmodel.MessageTemplateView;
import com.cditer.free.message.enums.MessageShowMode;
import com.cditer.free.message.service.IMessageInfoService;
import com.cditer.free.message.service.IMessageReceiveService;
import com.cditer.free.message.service.IMessageSendFactory;
import com.cditer.free.message.service.IMessageTemplateService;
import com.cditer.free.message.service.IReceiveGroupLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Component
public class MessageSendFactoryImpl implements IMessageSendFactory {

    @Autowired
    private IMessageTemplateService messageTemplateService;

    @Autowired
    private IMessageInfoService messageInfoService;

    @Autowired
    private IMessageReceiveService messageReceiveService;

    @Autowired
    private IReceiveGroupLinkService receiveGroupLinkService;

    @Autowired
    @Qualifier("loginModelQueryTokenImpl")
    private ILoginModelQuery loginModelQuery;

    @Override
    public <T> boolean sendMessage(MessageSendView<T> messageSendView) {
        MessageInfo messageInfo = formatMessageInfo(messageSendView.getMessageInfo(), messageSendView.getTempName(), messageSendView.getData());

        return sendMessage(messageInfo, messageSendView.getMessageReceives());
    }

    @Override
    public <T> MessageInfo formatMessageInfo(MessageInfo messageInfo, String tempName, T data) {
        if (messageInfo == null) {
            messageInfo = buildMessageInfo(tempName, data);
        } else {
            MessageInfo tempMessageInfo = buildMessageInfo(tempName, data);
            if (!StringUtils.hasText(messageInfo.getContent())) {
                messageInfo.setContent(tempMessageInfo.getContent());
            }
            if (!StringUtils.hasText(messageInfo.getTitle())) {
                messageInfo.setTitle(tempMessageInfo.getTitle());
            }
            if (!StringUtils.hasText(messageInfo.getIcon())) {
                messageInfo.setIcon(tempMessageInfo.getIcon());
            }
            if (!StringUtils.hasText(messageInfo.getType())) {
                messageInfo.setType(tempMessageInfo.getType());
            }
            if (messageInfo.getLevel() == null) {
                messageInfo.setLevel(tempMessageInfo.getLevel());
            }
            if (messageInfo.getAddDate() == null) {
                messageInfo.setAddDate(tempMessageInfo.getAddDate());
            }
            if (!StringUtils.hasText(messageInfo.getAddUserId())) {
                messageInfo.setAddUserId(tempMessageInfo.getAddUserId());
            }
            if (!StringUtils.hasText(messageInfo.getShowMode())) {
                messageInfo.setShowMode(tempMessageInfo.getShowMode());
            }
        }
        return messageInfo;
    }

    @Override
    public boolean sendMessage(MessageInfo messageInfo, List<MessageReceive> messageReceives) {
        if (messageInfo == null) {
            throw new BizException("要发送的消息不能为空");
        }
        if (CollectionUtils.isEmpty(messageReceives)) {
            throw new BizException("消息的接收方不能为空");
        }
        if (!StringUtils.hasText(messageInfo.getId())) {
            messageInfo.setId(PkIdUtils.getId());
        }

        for (MessageReceive messageReceive : messageReceives) {
            if (!StringUtils.hasText(messageReceive.getId())) {
                messageReceive.setId(PkIdUtils.getId());
            }
            messageReceive.setMessageId(messageInfo.getId());
        }

        boolean result = messageInfoService.addModel(messageInfo);
        if (result) {
            messageReceiveService.insertListEx(messageReceives);
        }
        return result;
    }

    @Override
    public <T> MessageInfo buildMessageInfo(String tempName, T data) {
        if (!StringUtils.hasText(tempName)) {
            throw new BizException("发送消息时，消息模板不能为空");
        }

        MessageTemplateView messageTemplateView = messageTemplateService.queryModelViewByName(tempName);
        if (messageTemplateView == null) {
            throw new BizException(String.format("发送消息时，找不到对应的消息模板[%s]", tempName));
        }

        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());

        // 设置参数
        Dict entity = Dict.create().set("data", data);
        entity.put("currDate", DateUtil.date());

        MessageInfo messageInfo = new MessageInfo();
        if (StringUtils.hasText(messageTemplateView.getContentTpl())) {
            // 创建模板
            Template contentTemplate = engine.getTemplate(messageTemplateView.getContentTpl());
            // 渲染
            String content = contentTemplate.render(entity);
            messageInfo.setContent(content);
        }

        if (StringUtils.hasText(messageTemplateView.getContentTpl())) {
            Template titleTemplate = engine.getTemplate(messageTemplateView.getContentTpl());
            String title = titleTemplate.render(entity);
            messageInfo.setTitle(title);
        }

        messageInfo.setIcon(messageTemplateView.getIcon());
        messageInfo.setLevel(messageTemplateView.getLevel());
        messageInfo.setType(messageTemplateView.getType());
        messageInfo.setShowMode(MessageShowMode.MESSAGE_PAGE.getValue());
        messageInfo.setAddDate(DateUtil.date());

        LoginModel currentLogin = loginModelQuery.getCurrentLogin();
        if (currentLogin != null) {
            messageInfo.setAddUserId(currentLogin.getId());
        }

        return messageInfo;
    }

    @Override
    public List<MessageReceive> buildReceiveByGroupName(String name) {
        return buildReceiveByGroupName(Arrays.asList(name));
    }

    @Override
    public List<MessageReceive> buildReceiveByGroupName(List<String> names) {
        return receiveGroupLinkService.queryMessageReceiveList(names);
    }

    @Override
    public <T> String buildMessageContent(String tempName, T data) {
        return buildMessageInfo(tempName, data).getContent();
    }

    @Override
    public <T> String buildMessageTitle(String tempName, T data) {
        return buildMessageInfo(tempName, data).getTitle();
    }
}
