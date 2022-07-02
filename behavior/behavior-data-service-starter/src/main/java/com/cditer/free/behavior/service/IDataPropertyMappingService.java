package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.DataPropertyMapping;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingSearch;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingView;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 22:00:35
 * @comment     属性映射表
 */

public interface IDataPropertyMappingService extends ISuperService<DataPropertyMapping>{

    /**
     * 根据搜索条件查询属性映射表条数
     * @param search 搜索条件
     * @return 属性映射表条数
     */
    int queryCountBySearch(DataPropertyMappingSearch search);

    /**
     * 根据搜索条件分页查询属性映射表对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 属性映射表对象集合
     */
    List<DataPropertyMappingView> queryListViewBySearch(DataPropertyMappingSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询属性映射表对象
     * @param search 搜索条件
     * @return 属性映射表对象
     */
    DataPropertyMappingView queryModelViewBySearch(DataPropertyMappingSearch search);

    /**
     * 根据id查询属性映射表对象
     * @param id 主键id
     * @return 属性映射表对象
     */
    DataPropertyMappingView queryModelViewById(String id);

    /**
     * 保存一个属性映射表对象
     * @param dataPropertyMapping 属性映射表对象
     * @return 是否成功
     */
    boolean saveDataPropertyMapping(DataPropertyMapping dataPropertyMapping);

}