package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.dao.IDataEditDtlDao;
import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlSearch;
import com.cditer.free.behavior.service.IDataEditDtlService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-04-27 21:12:49
 * @comment     数据修改详情
 */

@Component
public class DataEditDtlServiceImpl extends SuperService<DataEditDtl> implements IDataEditDtlService {
    @Autowired
    IDataEditDtlDao dataEditDtlDao;

    @Override
    public int queryCountBySearch(DataEditDtlSearch search) {
        return dataEditDtlDao.queryCountBySearch(search);
    }

    @Override
    public List<DataEditDtl> queryListBySearch(DataEditDtlSearch search, PagerModel pagerModel) {
        return dataEditDtlDao.queryListBySearch(search,pagerModel);
    }

}
