package com.cditer.free.devops.dao;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;
import com.cditer.free.devops.data.entity.model.ConfigPropertie;
import com.cditer.free.devops.data.entity.viewmodel.ConfigPropertieSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 08:45:53
 * @comment     属性配置表
 */

public interface IConfigPropertieDao extends ISuperDao<ConfigPropertie>{
    int queryCountBySearch(ConfigPropertieSearch search);

    List<ConfigPropertie> queryListBySearch(ConfigPropertieSearch search, PagerModel pagerModel);
}