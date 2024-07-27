package com.cditer.free.dbdevops.logical.service;

import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.dbdevops.data.model.TableSetting;
import com.cditer.free.dbdevops.data.viewmodel.TableSettingSearch;
import com.cditer.free.dbdevops.data.viewmodel.TableSettingView;
import com.cditer.free.dbdevops.data.viewmodel.dtview.DataListKeyValView;
import com.cditer.free.dbdevops.data.viewmodel.dtview.DataListQueryResp;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-06-14 12:34:58
 * @comment     表信息配置
 */

public interface ITableSettingService extends ISuperService<TableSetting>{

    /**
     * 根据搜索条件查询[表信息配置]条数
     * @param search 搜索条件
     * @return [表信息配置]条数
     */
    int queryCountBySearch(TableSettingSearch search);

    /**
     * 根据搜索条件分页查询[表信息配置]对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return [表信息配置]对象集合
     */
    List<TableSettingView> queryListViewBySearch(TableSettingSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询[表信息配置]对象
     * @param search 搜索条件
     * @return [表信息配置]对象
     */
    TableSettingView queryModelViewBySearch(TableSettingSearch search);

    /**
     * 根据id查询[表信息配置]对象
     * @param id 主键id
     * @return [表信息配置]对象
     */
    TableSettingView queryModelViewById(String id);

    /**
     * 保存一个[表信息配置]对象
     * @param tableSetting [表信息配置]对象
     * @return 是否成功
     */
    boolean saveTableSetting(TableSetting tableSetting);

    DataListQueryResp queryDataList(DataListKeyValView data, PagerModel pager);
}
