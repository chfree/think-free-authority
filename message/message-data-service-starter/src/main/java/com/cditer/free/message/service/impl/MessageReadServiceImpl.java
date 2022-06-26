package com.cditer.free.message.service.impl;

import com.cditer.free.message.entity.model.MessageRead;
import com.cditer.free.message.entity.viewmodel.MessageReadSearch;
import com.cditer.free.message.entity.viewmodel.MessageReadView;
import com.cditer.free.message.mapper.IMessageReadMapper;
import com.cditer.free.message.service.IMessageReadService;
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
 * @createtime  2022-06-26 17:13:00
 * @comment     消息读记录
 */

@Component
public class MessageReadServiceImpl extends SuperService<MessageRead> implements IMessageReadService {

    @Autowired
    protected IMessageReadMapper messageReadMapper;

    @Override
    public int queryCountBySearch(MessageReadSearch search) {
        return messageReadMapper.queryCountBySearch(search);
    }

    @Override
    public List<MessageReadView> queryListViewBySearch(MessageReadSearch search, PagerModel pagerModel) {
        return messageReadMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public MessageReadView queryModelViewBySearch(MessageReadSearch search) {
        return messageReadMapper.queryModelViewBySearch(search);
    }

    @Override
    public MessageReadView queryModelViewById(String id) {
        MessageReadSearch search = new MessageReadSearch();
        search.setId(id);

        return messageReadMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveMessageRead(MessageRead messageRead) {
        if(StringUtils.hasText(messageRead.getId())){
            messageRead.setModelStatus(ModelStatus.update);
        }else{
            messageRead.setId(PkIdUtils.getId());
            messageRead.setModelStatus(ModelStatus.add);
        }

        return applyChange(messageRead);
    }
}
