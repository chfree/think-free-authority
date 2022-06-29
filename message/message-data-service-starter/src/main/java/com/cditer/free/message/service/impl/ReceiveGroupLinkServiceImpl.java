package com.cditer.free.message.service.impl;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.message.entity.model.MessageReceive;
import com.cditer.free.message.entity.model.ReceiveGroupLink;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupLinkSearch;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupLinkView;
import com.cditer.free.message.mapper.IReceiveGroupLinkMapper;
import com.cditer.free.message.service.IReceiveGroupLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:48:31
 * @comment     消息组连接
 */

@Component
public class ReceiveGroupLinkServiceImpl extends SuperService<ReceiveGroupLink> implements IReceiveGroupLinkService {

    @Autowired
    protected IReceiveGroupLinkMapper receiveGroupLinkMapper;

    @Override
    public int queryCountBySearch(ReceiveGroupLinkSearch search) {
        return receiveGroupLinkMapper.queryCountBySearch(search);
    }

    @Override
    public List<ReceiveGroupLinkView> queryListViewBySearch(ReceiveGroupLinkSearch search, PagerModel pagerModel) {
        return receiveGroupLinkMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public ReceiveGroupLinkView queryModelViewBySearch(ReceiveGroupLinkSearch search) {
        return receiveGroupLinkMapper.queryModelViewBySearch(search);
    }

    @Override
    public ReceiveGroupLinkView queryModelViewById(String id) {
        ReceiveGroupLinkSearch search = new ReceiveGroupLinkSearch();
        search.setId(id);

        return receiveGroupLinkMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean savereceiveGroupLink(ReceiveGroupLink receiveGroupLink) {
        receiveGroupLink.autoPkIdAndStatus();

        return applyChange(receiveGroupLink);
    }

    @Override
    public List<MessageReceive> queryMessageReceiveList(List<String> groupNames) {
        if(CollectionUtils.isEmpty(groupNames)){
            return null;
        }
        return receiveGroupLinkMapper.queryMessageReceiveList(groupNames);
    }
}
