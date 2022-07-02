package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlView;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 21:52:48
 * @comment     数据修改详情
 */

public interface IDataEditDtlService extends ISuperService<DataEditDtl>{

    /**
     * 根据搜索条件查询数据修改详情条数
     * @param search 搜索条件
     * @return 数据修改详情条数
     */
    int queryCountBySearch(DataEditDtlSearch search);

    /**
     * 根据搜索条件分页查询数据修改详情对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 数据修改详情对象集合
     */
    List<DataEditDtlView> queryListViewBySearch(DataEditDtlSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询数据修改详情对象
     * @param search 搜索条件
     * @return 数据修改详情对象
     */
    DataEditDtlView queryModelViewBySearch(DataEditDtlSearch search);

    /**
     * 根据id查询数据修改详情对象
     * @param id 主键id
     * @return 数据修改详情对象
     */
    DataEditDtlView queryModelViewById(String id);

    /**
     * 保存一个数据修改详情对象
     * @param dataEditDtl 数据修改详情对象
     * @return 是否成功
     */
    boolean saveDataEditDtl(DataEditDtl dataEditDtl);

}
