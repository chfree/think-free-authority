package com.cditer.free.dbdevops.logical.service;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.dbdevops.data.model.ColumnSetting;
import com.cditer.free.dbdevops.data.viewmodel.ColumnSettingSearch;
import com.cditer.free.dbdevops.data.viewmodel.ColumnSettingView;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-07-25 12:59:11
 * @comment     列信息配置
 */

public interface IColumnSettingService extends ISuperService<ColumnSetting>{

    /**
     * 根据搜索条件查询[列信息配置]条数
     * @param search 搜索条件
     * @return [列信息配置]条数
     */
    int queryCountBySearch(ColumnSettingSearch search);

    /**
     * 根据搜索条件分页查询[列信息配置]对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return [列信息配置]对象集合
     */
    List<ColumnSettingView> queryListViewBySearch(ColumnSettingSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询[列信息配置]对象
     * @param search 搜索条件
     * @return [列信息配置]对象
     */
    ColumnSettingView queryModelViewBySearch(ColumnSettingSearch search);

    /**
     * 根据id查询[列信息配置]对象
     * @param id 主键id
     * @return [列信息配置]对象
     */
    ColumnSettingView queryModelViewById(String id);

    /**
     * 保存一个[列信息配置]对象
     * @param columnSetting [列信息配置]对象
     * @return 是否成功
     */
    boolean saveColumnSetting(ColumnSetting columnSetting);

}
