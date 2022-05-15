package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.dao.IDataEntityInfoDao;
import com.cditer.free.behavior.entity.model.DataEntityInfo;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoSearch;
import com.cditer.free.behavior.service.IDataEntityInfoService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-05-15 21:45:03
 * @comment     数据实体信息
 */

@Component
public class DataEntityInfoServiceImpl extends SuperService<DataEntityInfo> implements IDataEntityInfoService {
    @Autowired
    IDataEntityInfoDao dataEntityInfoDao;

    @Override
    public int queryCountBySearch(DataEntityInfoSearch search) {
        return dataEntityInfoDao.queryCountBySearch(search);
    }

    @Override
    public List<DataEntityInfo> queryListBySearch(DataEntityInfoSearch search, PagerModel pagerModel) {
        return dataEntityInfoDao.queryListBySearch(search,pagerModel);
    }

}
