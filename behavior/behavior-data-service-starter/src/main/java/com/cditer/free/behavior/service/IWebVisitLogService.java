package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.WebVisitLog;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogSearch;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogView;
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
    /**
     * 根据搜索条件查询web访问日志表条数
     * @param search 搜索条件
     * @return web访问日志表条数
     */
    int queryCountBySearch(WebVisitLogSearch search);

    /**
     * 根据搜索条件分页查询web访问日志表对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return web访问日志表对象集合
     */
    List<WebVisitLogView> queryListViewBySearch(WebVisitLogSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询web访问日志表对象
     * @param search 搜索条件
     * @return web访问日志表对象
     */
    WebVisitLogView queryModelViewBySearch(WebVisitLogSearch search);

    /**
     * 根据id查询web访问日志表对象
     * @param id 主键id
     * @return web访问日志表对象
     */
    WebVisitLogView queryModelViewById(String id);

    /**
     * 保存一个web访问日志表对象
     * @param webVisitLog web访问日志表对象
     * @return 是否成功
     */
    boolean saveWebVisitLog(WebVisitLog webVisitLog);

    void batchSaveOperLog(List<WebVisitLog> operLogs);

    void batchSaveOperLog(List<WebVisitLog> operLogs, LoginModel loginModel);

    int queryVisitCountBySearch(WebVisitLogSearch search);
}
