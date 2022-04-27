package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-04-27 21:11:11
 * @comment     数据修改详情
 */

public interface IDataEditDtlService extends ISuperService<DataEditDtl> {
    int queryCountBySearch(DataEditDtlSearch search);

    List<DataEditDtl> queryListBySearch(DataEditDtlSearch search, PagerModel pagerModel);
}
