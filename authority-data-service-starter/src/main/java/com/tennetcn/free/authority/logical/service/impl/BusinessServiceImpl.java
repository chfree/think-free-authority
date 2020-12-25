package com.tennetcn.free.authority.logical.service.impl;

import com.tennetcn.free.authority.logical.dao.IBusinessDao;
import com.tennetcn.free.authority.data.entity.model.Business;
import com.tennetcn.free.authority.logical.service.IBusinessService;
import com.tennetcn.free.authority.data.entity.viewmodel.BusinessSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:12
 * @comment
 */

@Component
public class BusinessServiceImpl extends SuperService<Business> implements IBusinessService {

    @Autowired
    IBusinessDao businessDao;

    @Override
    public int queryCountBySearch(BusinessSearch search) {
        return businessDao.queryCountBySearch(search);
    }

    @Override
    public List<Business> queryListBySearch(BusinessSearch search, PagerModel pagerModel) {
        return businessDao.queryListBySearch(search,pagerModel);
    }
}
