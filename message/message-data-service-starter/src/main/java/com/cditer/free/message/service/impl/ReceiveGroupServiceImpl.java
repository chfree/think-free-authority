package com.cditer.free.message.service.impl;

import com.cditer.free.message.entity.model.ReceiveGroup;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupSearch;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupView;
import com.cditer.free.message.mapper.IReceiveGroupMapper;
import com.cditer.free.message.service.IReceiveGroupService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.core.message.data.PagerModel;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:34:12
 * @comment     消息组
 */

@Component
public class ReceiveGroupServiceImpl extends SuperService<ReceiveGroup> implements IReceiveGroupService {

    @Autowired
    protected IReceiveGroupMapper receiveGroupMapper;

    @Override
    public int queryCountBySearch(ReceiveGroupSearch search) {
        return receiveGroupMapper.queryCountBySearch(search);
    }

    @Override
    public List<ReceiveGroupView> queryListViewBySearch(ReceiveGroupSearch search, PagerModel pagerModel) {
        return receiveGroupMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public ReceiveGroupView queryModelViewBySearch(ReceiveGroupSearch search) {
        return receiveGroupMapper.queryModelViewBySearch(search);
    }

    @Override
    public ReceiveGroupView queryModelViewById(String id) {
        ReceiveGroupSearch search = new ReceiveGroupSearch();
        search.setId(id);

        return receiveGroupMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean savereceiveGroup(ReceiveGroup receiveGroup) {
        receiveGroup.autoPkIdAndStatus();

        return applyChange(receiveGroup);
    }
}
