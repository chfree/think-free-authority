package com.cditer.free.behavior.dao;

import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-04-27 21:03:38
 * @comment     数据修改记录
 */

public interface IDataEditLogDao extends ISuperDao<DataEditLog> {
    int queryCountBySearch(DataEditLogSearch search);

    List<DataEditLog> queryListBySearch(DataEditLogSearch search, PagerModel pagerModel);
}
