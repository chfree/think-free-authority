package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.dao.IWebVisitLimitDao;
import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitSearch;
import com.cditer.free.behavior.service.IWebVisitLimitService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-15 12:52:34
 * @comment     web访问控制表
 */

@Component
public class WebVisitLimitServiceImpl extends SuperService<WebVisitLimit> implements IWebVisitLimitService {
    @Autowired
    IWebVisitLimitDao webVisitLimitDao;

    @Override
    public int queryCountBySearch(WebVisitLimitSearch search) {
        return webVisitLimitDao.queryCountBySearch(search);
    }

    @Override
    public List<WebVisitLimit> queryListBySearch(WebVisitLimitSearch search, PagerModel pagerModel) {
        return webVisitLimitDao.queryListBySearch(search,pagerModel);
    }

}
