package com.cditer.free.behavior.dao;

import com.cditer.free.behavior.entity.model.WebVisitLog;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-14 12:15:39
 * @comment     操作日志表
 */

public interface IWebVisitLogDao extends ISuperDao<WebVisitLog> {
    int queryCountBySearch(WebVisitLogSearch search);

    List<WebVisitLog> queryListBySearch(WebVisitLogSearch search, PagerModel pagerModel);
}
