package com.cditer.free.param.logical.dao;


import com.cditer.free.param.data.entity.model.ParamSetting;
import com.cditer.free.param.data.entity.viewmodel.ParamSettingSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 11:27:20
 * @comment     参数配置表
 */

public interface IParamSettingDao extends ISuperDao<ParamSetting>{
    int queryCountBySearch(ParamSettingSearch search);

    List<ParamSetting> queryListBySearch(ParamSettingSearch search, PagerModel pagerModel);

    ParamSetting queryModelBySearch(ParamSettingSearch search);
}