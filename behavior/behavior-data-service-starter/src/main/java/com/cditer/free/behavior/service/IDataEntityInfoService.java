package com.cditer.free.behavior.service;

import com.cditer.free.behavior.entity.model.DataEntityInfo;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-05-15 21:43:52
 * @comment     数据实体信息
 */

public interface IDataEntityInfoService extends ISuperService<DataEntityInfo> {
    int queryCountBySearch(DataEntityInfoSearch search);

    List<DataEntityInfo> queryListBySearch(DataEntityInfoSearch search, PagerModel pagerModel);
}