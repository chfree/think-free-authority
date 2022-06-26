package com.cditer.free.message.service;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.message.entity.model.MessageTemplate;
import com.cditer.free.message.entity.viewmodel.MessageTemplateSearch;
import com.cditer.free.message.entity.viewmodel.MessageTemplateView;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-24 12:45:44
 * @comment     消息模板表
 */

public interface IMessageTemplateService extends ISuperService<MessageTemplate> {
    int queryCountBySearch(MessageTemplateSearch search);

    List<MessageTemplateView> queryListViewBySearch(MessageTemplateSearch search, PagerModel pagerModel);

    MessageTemplateView queryModelViewBySearch(MessageTemplateSearch search);

    MessageTemplateView queryModelViewById(String id);

    MessageTemplateView queryModelViewByName(String name);

    boolean saveMessageTemplate(MessageTemplate messageTemplate);
}
