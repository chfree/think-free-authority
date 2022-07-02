package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitSearch;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitView;
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

    /**
     * 根据搜索条件查询web访问控制表条数
     * @param search 搜索条件
     * @return web访问控制表条数
     */
    int queryCountBySearch(WebVisitLimitSearch search);

    /**
     * 根据搜索条件分页查询web访问控制表对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return web访问控制表对象集合
     */
    List<WebVisitLimitView> queryListViewBySearch(WebVisitLimitSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询web访问控制表对象
     * @param search 搜索条件
     * @return web访问控制表对象
     */
    WebVisitLimitView queryModelViewBySearch(WebVisitLimitSearch search);

    /**
     * 根据id查询web访问控制表对象
     * @param id 主键id
     * @return web访问控制表对象
     */
    WebVisitLimitView queryModelViewById(String id);

    /**
     * 保存一个web访问控制表对象
     * @param webVisitLimit web访问控制表对象
     * @return 是否成功
     */
    boolean saveWebVisitLimit(WebVisitLimit webVisitLimit);
}
