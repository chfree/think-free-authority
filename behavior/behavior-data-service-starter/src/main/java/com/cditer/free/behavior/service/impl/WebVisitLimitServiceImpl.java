package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitSearch;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitView;
import com.cditer.free.behavior.mapper.IWebVisitLimitMapper;
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
    private IWebVisitLimitMapper webVisitLimitMapper;

    @Override
    public int queryCountBySearch(WebVisitLimitSearch search) {
        return webVisitLimitMapper.queryCountBySearch(search);
    }

    @Override
    public List<WebVisitLimitView> queryListViewBySearch(WebVisitLimitSearch search, PagerModel pagerModel) {
        return webVisitLimitMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public WebVisitLimitView queryModelViewBySearch(WebVisitLimitSearch search) {
        return webVisitLimitMapper.queryModelViewBySearch(search);
    }

    @Override
    public WebVisitLimitView queryModelViewById(String id) {
        WebVisitLimitSearch search = new WebVisitLimitSearch();
        search.setId(id);

        return webVisitLimitMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveWebVisitLimit(WebVisitLimit webVisitLimit) {
        webVisitLimit.autoPkIdAndStatus();

        return applyChange(webVisitLimit);
    }
}
