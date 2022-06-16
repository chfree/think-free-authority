package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-15 12:52:08
 * @comment     web访问控制表
 */

public interface IWebVisitLimitService extends ISuperService<WebVisitLimit> {
    int queryCountBySearch(WebVisitLimitSearch search);

    List<WebVisitLimit> queryListBySearch(WebVisitLimitSearch search, PagerModel pagerModel);

    WebVisitLimit queryModelBySearch(WebVisitLimitSearch search);
}
