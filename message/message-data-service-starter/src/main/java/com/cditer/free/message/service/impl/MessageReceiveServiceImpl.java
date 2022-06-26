package com.cditer.free.message.service.impl;

import com.cditer.free.message.entity.model.MessageReceive;
import com.cditer.free.message.entity.viewmodel.MessageReceiveSearch;
import com.cditer.free.message.entity.viewmodel.MessageReceiveView;
import com.cditer.free.message.mapper.IMessageReceiveMapper;
import com.cditer.free.message.service.IMessageReceiveService;
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
 * @createtime  2022-06-26 17:14:29
 * @comment     消息接收人
 */

@Component
public class MessageReceiveServiceImpl extends SuperService<MessageReceive> implements IMessageReceiveService {

    @Autowired
    protected IMessageReceiveMapper messageReceiveMapper;

    @Override
    public int queryCountBySearch(MessageReceiveSearch search) {
        return messageReceiveMapper.queryCountBySearch(search);
    }

    @Override
    public List<MessageReceiveView> queryListViewBySearch(MessageReceiveSearch search, PagerModel pagerModel) {
        return messageReceiveMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public MessageReceiveView queryModelViewBySearch(MessageReceiveSearch search) {
        return messageReceiveMapper.queryModelViewBySearch(search);
    }

    @Override
    public MessageReceiveView queryModelViewById(String id) {
        MessageReceiveSearch search = new MessageReceiveSearch();
        search.setId(id);

        return messageReceiveMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveMessageReceive(MessageReceive messageReceive) {
        if(StringUtils.hasText(messageReceive.getId())){
            messageReceive.setModelStatus(ModelStatus.update);
        }else{
            messageReceive.setId(PkIdUtils.getId());
            messageReceive.setModelStatus(ModelStatus.add);
        }

        return applyChange(messageReceive);
    }
}
