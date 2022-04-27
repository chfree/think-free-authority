package com.cditer.free.behavior.dao;

import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-04-27 21:10:01
 * @comment     数据修改详情
 */

public interface IDataEditDtlDao extends ISuperDao<DataEditDtl> {
    int queryCountBySearch(DataEditDtlSearch search);

    List<DataEditDtl> queryListBySearch(DataEditDtlSearch search, PagerModel pagerModel);
}
