package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.WebVisitLog;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-14 12:16:56
 * @comment     操作日志表
 */

public interface IWebVisitLogService extends ISuperService<WebVisitLog> {
    int queryCountBySearch(WebVisitLogSearch search);

    List<WebVisitLog> queryListBySearch(WebVisitLogSearch search, PagerModel pagerModel);

    void batchSaveOperLog(List<WebVisitLog> operLogs);

    void batchSaveOperLog(List<WebVisitLog> operLogs, LoginModel loginModel);
}
