package com.cditer.free.devops.logical.service.impl;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.devops.logical.dao.IConfigPropertieDao;
import com.cditer.free.devops.data.entity.model.ConfigPropertie;
import com.cditer.free.devops.data.entity.viewmodel.ConfigPropertieSearch;
import com.cditer.free.devops.logical.service.IConfigPropertieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 08:48:02
 * @comment     属性配置表
 */

@Component
public class ConfigPropertieServiceImpl extends SuperService<ConfigPropertie> implements IConfigPropertieService{
    @Autowired
    IConfigPropertieDao configPropertieDao;

    @Override
    public int queryCountBySearch(ConfigPropertieSearch search) {
        return configPropertieDao.queryCountBySearch(search);
    }

    @Override
    public List<ConfigPropertie> queryListBySearch(ConfigPropertieSearch search, PagerModel pagerModel) {
        return configPropertieDao.queryListBySearch(search,pagerModel);
    }

}