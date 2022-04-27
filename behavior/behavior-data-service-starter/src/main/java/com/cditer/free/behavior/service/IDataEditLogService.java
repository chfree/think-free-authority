package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.base.IBehaviorModel;
import com.cditer.free.behavior.entity.base.IBehaviorQueryDb;
import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
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
    int queryCountBySearch(DataEditLogSearch search);

    List<DataEditLog> queryListBySearch(DataEditLogSearch search, PagerModel pagerModel);

    /**
     * 保存数据修改日志
     */
    void saveListEditLog(List<? extends IBehaviorModel> list, LoginModel loginModel);

    /**
     * 保存数据修改日志及详情
     */
    void saveListEditLog(List<? extends IBehaviorModel> list, IBehaviorQueryDb behaviorQueryDb, LoginModel loginModel);


}
