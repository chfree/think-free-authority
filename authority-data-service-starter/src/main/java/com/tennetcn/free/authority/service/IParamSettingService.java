package com.tennetcn.free.authority.service;


import com.tennetcn.free.authority.data.entity.model.ParamSetting;
import com.tennetcn.free.authority.data.entity.viewmodel.ParamSettingSearch;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.core.message.data.PagerModel;
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

    String queryStrValue(String defineName);

    String queryStrValue(String defineName,String defaultValue);

    int queryIntValue(String defineName);

    int queryIntValue(String defineName,int defaultValue);

    double queryDoubleValue(String defineName);

    double queryDoubleValue(String defineName,double defaultValue);

    float queryFloatValue(String defineName);

    float queryFloatValue(String defineName,float defaultValue);
}