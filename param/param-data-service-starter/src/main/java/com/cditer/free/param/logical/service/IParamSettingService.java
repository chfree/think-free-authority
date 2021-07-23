package com.cditer.free.param.logical.service;


import com.cditer.free.param.data.entity.model.ParamSetting;
import com.cditer.free.param.data.entity.viewmodel.ParamSettingSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 11:29:11
 * @comment     参数配置表
 */

public interface IParamSettingService extends ISuperService<ParamSetting>{
    int queryCountBySearch(ParamSettingSearch search);

    List<ParamSetting> queryListBySearch(ParamSettingSearch search, PagerModel pagerModel);

    ParamSetting queryModelByName(String name);

    String queryStrValue(String name);

    String queryStrValue(String name,String defaultValue);

    int queryIntValue(String name);

    int queryIntValue(String name,int defaultValue);

    double queryDoubleValue(String name);

    double queryDoubleValue(String name,double defaultValue);

    float queryFloatValue(String name);

    float queryFloatValue(String name,float defaultValue);
}