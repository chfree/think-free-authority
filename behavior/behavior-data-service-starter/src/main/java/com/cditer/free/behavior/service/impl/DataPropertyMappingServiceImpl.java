package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.entity.model.DataPropertyMapping;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingSearch;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingView;
import com.cditer.free.behavior.mapper.IDataPropertyMappingMapper;
import com.cditer.free.behavior.service.IDataPropertyMappingService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 22:00:52
 * @comment     属性映射表
 */

@Component
public class DataPropertyMappingServiceImpl extends SuperService<DataPropertyMapping> implements IDataPropertyMappingService {

    @Autowired
    protected IDataPropertyMappingMapper dataPropertyMappingMapper;

    @Override
    public int queryCountBySearch(DataPropertyMappingSearch search) {
        return dataPropertyMappingMapper.queryCountBySearch(search);
    }

    @Override
    public List<DataPropertyMappingView> queryListViewBySearch(DataPropertyMappingSearch search, PagerModel pagerModel) {
        return dataPropertyMappingMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public DataPropertyMappingView queryModelViewBySearch(DataPropertyMappingSearch search) {
        return dataPropertyMappingMapper.queryModelViewBySearch(search);
    }

    @Override
    public DataPropertyMappingView queryModelViewById(String id) {
        DataPropertyMappingSearch search = new DataPropertyMappingSearch();
        search.setId(id);

        return dataPropertyMappingMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveDataPropertyMapping(DataPropertyMapping dataPropertyMapping) {
        dataPropertyMapping.autoPkIdAndStatus();

        return applyChange(dataPropertyMapping);
    }
}