package com.cditer.free.message.service.impl;

import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.message.entity.model.MessageInfo;
import com.cditer.free.message.entity.viewmodel.MessageInfoSearch;
import com.cditer.free.message.entity.viewmodel.MessageInfoView;
import com.cditer.free.message.mapper.IMessageInfoMapper;
import com.cditer.free.message.service.IMessageInfoService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.data.PagerModel;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 16:42:19
 * @comment     消息表
 */

@Component
public class MessageInfoServiceImpl extends SuperService<MessageInfo> implements IMessageInfoService {

    @Autowired
    protected IMessageInfoMapper messageInfoMapper;

    @Override
    public int queryCountBySearch(MessageInfoSearch search) {
        return messageInfoMapper.queryCountBySearch(search);
    }

    @Override
    public List<MessageInfoView> queryListViewBySearch(MessageInfoSearch search, PagerModel pagerModel) {
        return messageInfoMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public MessageInfoView queryModelViewBySearch(MessageInfoSearch search) {
        return messageInfoMapper.queryModelViewBySearch(search);
    }

    @Override
    public MessageInfoView queryModelViewById(String id) {
        MessageInfoSearch search = new MessageInfoSearch();
        search.setId(id);

        return messageInfoMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveMessageInfo(MessageInfo messageInfo) {
        if(StringUtils.hasText(messageInfo.getId())){
            messageInfo.setModelStatus(ModelStatus.update);
        }else{
            messageInfo.setId(PkIdUtils.getId());
            messageInfo.setModelStatus(ModelStatus.add);
        }

        return applyChange(messageInfo);
    }
}