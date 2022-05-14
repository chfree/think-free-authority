package com.cditer.free.behavior.dao;

import com.cditer.free.behavior.entity.model.DataPropertyMapping;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-05-14 10:25:33
 * @comment     属性映射表
 */

public interface IDataPropertyMappingDao extends ISuperDao<DataPropertyMapping> {
    int queryCountBySearch(DataPropertyMappingSearch search);

    List<DataPropertyMapping> queryListBySearch(DataPropertyMappingSearch search, PagerModel pagerModel);
}
