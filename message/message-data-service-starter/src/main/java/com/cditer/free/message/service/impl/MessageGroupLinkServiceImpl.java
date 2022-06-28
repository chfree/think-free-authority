package com.cditer.free.message.service.impl;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.message.entity.model.MessageGroupLink;
import com.cditer.free.message.entity.viewmodel.MessageGroupLinkSearch;
import com.cditer.free.message.entity.viewmodel.MessageGroupLinkView;
import com.cditer.free.message.mapper.IMessageGroupLinkMapper;
import com.cditer.free.message.service.IMessageGroupLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:48:31
 * @comment     消息组连接
 */

@Component
public class MessageGroupLinkServiceImpl extends SuperService<MessageGroupLink> implements IMessageGroupLinkService {

    @Autowired
    protected IMessageGroupLinkMapper messageGroupLinkMapper;

    @Override
    public int queryCountBySearch(MessageGroupLinkSearch search) {
        return messageGroupLinkMapper.queryCountBySearch(search);
    }

    @Override
    public List<MessageGroupLinkView> queryListViewBySearch(MessageGroupLinkSearch search, PagerModel pagerModel) {
        return messageGroupLinkMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public MessageGroupLinkView queryModelViewBySearch(MessageGroupLinkSearch search) {
        return messageGroupLinkMapper.queryModelViewBySearch(search);
    }

    @Override
    public MessageGroupLinkView queryModelViewById(String id) {
        MessageGroupLinkSearch search = new MessageGroupLinkSearch();
        search.setId(id);

        return messageGroupLinkMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveMessageGroupLink(MessageGroupLink messageGroupLink) {
        messageGroupLink.autoPkIdAndStatus();

        return applyChange(messageGroupLink);
    }
}
