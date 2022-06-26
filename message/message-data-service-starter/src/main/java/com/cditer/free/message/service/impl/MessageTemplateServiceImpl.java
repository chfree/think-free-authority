package com.cditer.free.message.service.impl;

import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.message.entity.model.MessageTemplate;
import com.cditer.free.message.entity.viewmodel.MessageTemplateSearch;
import com.cditer.free.message.entity.viewmodel.MessageTemplateView;
import com.cditer.free.message.mapper.IMessageTemplateMapper;
import com.cditer.free.message.service.IMessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-24 12:47:08
 * @comment     消息模板表
 */

@Component
public class MessageTemplateServiceImpl extends SuperService<MessageTemplate> implements IMessageTemplateService {

    @Autowired
    private IMessageTemplateMapper messageTemplateMapper;

    @Override
    public int queryCountBySearch(MessageTemplateSearch search) {
        return messageTemplateMapper.queryCountBySearch(search);
    }

    @Override
    public List<MessageTemplateView> queryListViewBySearch(MessageTemplateSearch search, PagerModel pagerModel) {
        return messageTemplateMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public MessageTemplateView queryModelViewBySearch(MessageTemplateSearch search) {
        return messageTemplateMapper.queryModelViewBySearch(search);
    }

    @Override
    public MessageTemplateView queryModelViewById(String id) {
        MessageTemplateSearch search = new MessageTemplateSearch();
        search.setId(id);

        return messageTemplateMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveMessageTemplate(MessageTemplate messageTemplate) {
        if(StringUtils.isEmpty(messageTemplate.getId())){
            messageTemplate.setId(PkIdUtils.getId());
            messageTemplate.setModelStatus(ModelStatus.add);
        }else{
            messageTemplate.setModelStatus(ModelStatus.update);
        }

        return applyChange(messageTemplate);
    }
}