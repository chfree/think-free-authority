package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IBusinessDao;
import com.tennetcn.free.authority.model.Business;
import com.tennetcn.free.authority.service.IBusinessService;
import com.tennetcn.free.authority.viewmodel.BusinessSearch;
import com.tennetcn.free.core.message.PagerModel;
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
