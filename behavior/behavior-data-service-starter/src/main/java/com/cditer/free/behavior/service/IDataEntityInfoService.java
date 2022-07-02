package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.DataEntityInfo;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoView;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 21:58:27
 * @comment     数据实体信息
 */

public interface IDataEntityInfoService extends ISuperService<DataEntityInfo>{

    /**
     * 根据搜索条件查询数据实体信息条数
     * @param search 搜索条件
     * @return 数据实体信息条数
     */
    int queryCountBySearch(DataEntityInfoSearch search);

    /**
     * 根据搜索条件分页查询数据实体信息对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 数据实体信息对象集合
     */
    List<DataEntityInfoView> queryListViewBySearch(DataEntityInfoSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询数据实体信息对象
     * @param search 搜索条件
     * @return 数据实体信息对象
     */
    DataEntityInfoView queryModelViewBySearch(DataEntityInfoSearch search);

    /**
     * 根据id查询数据实体信息对象
     * @param id 主键id
     * @return 数据实体信息对象
     */
    DataEntityInfoView queryModelViewById(String id);

    /**
     * 保存一个数据实体信息对象
     * @param dataEntityInfo 数据实体信息对象
     * @return 是否成功
     */
    boolean saveDataEntityInfo(DataEntityInfo dataEntityInfo);

}