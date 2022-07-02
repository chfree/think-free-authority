package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogView;
import com.cditer.free.core.message.data.IDbModel;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-04-27 21:06:29
 * @comment     数据修改记录
 */

public interface IDataEditLogService extends ISuperService<DataEditLog> {
    /**
     * 根据搜索条件查询数据修改记录条数
     * @param search 搜索条件
     * @return 数据修改记录条数
     */
    int queryCountBySearch(DataEditLogSearch search);

    /**
     * 根据搜索条件分页查询数据修改记录对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 数据修改记录对象集合
     */
    List<DataEditLogView> queryListViewBySearch(DataEditLogSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询数据修改记录对象
     * @param search 搜索条件
     * @return 数据修改记录对象
     */
    DataEditLogView queryModelViewBySearch(DataEditLogSearch search);

    /**
     * 根据id查询数据修改记录对象
     * @param id 主键id
     * @return 数据修改记录对象
     */
    DataEditLogView queryModelViewById(String id);

    /**
     * 保存一个数据修改记录对象
     * @param dataEditLog 数据修改记录对象
     * @return 是否成功
     */
    boolean saveDataEditLog(DataEditLog dataEditLog);

    /**
     * 保存数据修改日志
     */
    void saveListEditLog(List<? extends IDbModel> list, LoginModel loginModel);
}
