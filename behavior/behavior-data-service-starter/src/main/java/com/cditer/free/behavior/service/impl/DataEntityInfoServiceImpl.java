package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.entity.model.DataEntityInfo;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoView;
import com.cditer.free.behavior.mapper.IDataEntityInfoMapper;
import com.cditer.free.behavior.service.IDataEntityInfoService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 21:58:51
 * @comment     数据实体信息
 */

@Component
public class DataEntityInfoServiceImpl extends SuperService<DataEntityInfo> implements IDataEntityInfoService{

    @Autowired
    protected IDataEntityInfoMapper dataEntityInfoMapper;

    @Override
    public int queryCountBySearch(DataEntityInfoSearch search) {
        return dataEntityInfoMapper.queryCountBySearch(search);
    }

    @Override
    public List<DataEntityInfoView> queryListViewBySearch(DataEntityInfoSearch search, PagerModel pagerModel) {
        return dataEntityInfoMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public DataEntityInfoView queryModelViewBySearch(DataEntityInfoSearch search) {
        return dataEntityInfoMapper.queryModelViewBySearch(search);
    }

    @Override
    public DataEntityInfoView queryModelViewById(String id) {
        DataEntityInfoSearch search = new DataEntityInfoSearch();
        search.setId(id);

        return dataEntityInfoMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveDataEntityInfo(DataEntityInfo dataEntityInfo) {
        dataEntityInfo.autoPkIdAndStatus();

        return applyChange(dataEntityInfo);
    }
}