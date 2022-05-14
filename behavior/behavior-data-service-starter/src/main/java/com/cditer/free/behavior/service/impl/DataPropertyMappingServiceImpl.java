package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.dao.IDataPropertyMappingDao;
import com.cditer.free.behavior.entity.model.DataPropertyMapping;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingSearch;
import com.cditer.free.behavior.service.IDataPropertyMappingService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-05-14 10:32:44
 * @comment     属性映射表
 */

@Component
public class DataPropertyMappingServiceImpl extends SuperService<DataPropertyMapping> implements IDataPropertyMappingService {
    @Autowired
    IDataPropertyMappingDao dataPropertyMappingDao;

    @Override
    public int queryCountBySearch(DataPropertyMappingSearch search) {
        return dataPropertyMappingDao.queryCountBySearch(search);
    }

    @Override
    public List<DataPropertyMapping> queryListBySearch(DataPropertyMappingSearch search, PagerModel pagerModel) {
        return dataPropertyMappingDao.queryListBySearch(search,pagerModel);
    }

}