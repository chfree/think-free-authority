package com.cditer.free.message.service.impl;

import com.cditer.free.message.entity.model.MessageGroup;
import com.cditer.free.message.entity.viewmodel.MessageGroupSearch;
import com.cditer.free.message.entity.viewmodel.MessageGroupView;
import com.cditer.free.message.mapper.IMessageGroupMapper;
import com.cditer.free.message.service.IMessageGroupService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.core.message.data.PagerModel;
import org.springframework.util.StringUtils;
import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:34:12
 * @comment     消息组
 */

@Component
public class MessageGroupServiceImpl extends SuperService<MessageGroup> implements IMessageGroupService {

    @Autowired
    protected IMessageGroupMapper messageGroupMapper;

    @Override
    public int queryCountBySearch(MessageGroupSearch search) {
        return messageGroupMapper.queryCountBySearch(search);
    }

    @Override
    public List<MessageGroupView> queryListViewBySearch(MessageGroupSearch search, PagerModel pagerModel) {
        return messageGroupMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public MessageGroupView queryModelViewBySearch(MessageGroupSearch search) {
        return messageGroupMapper.queryModelViewBySearch(search);
    }

    @Override
    public MessageGroupView queryModelViewById(String id) {
        MessageGroupSearch search = new MessageGroupSearch();
        search.setId(id);

        return messageGroupMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveMessageGroup(MessageGroup messageGroup) {
        messageGroup.autoPkIdAndStatus();

        return applyChange(messageGroup);
    }
}
