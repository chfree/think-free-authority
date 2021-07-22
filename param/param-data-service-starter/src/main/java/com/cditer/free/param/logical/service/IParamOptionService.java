package com.cditer.free.param.logical.service;

import com.cditer.free.param.data.entity.model.ParamOption;
import com.cditer.free.param.data.entity.viewmodel.ParamOptionSearch;
import com.cditer.free.param.data.entity.viewmodel.ParamOptionView;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;
import java.util.Map;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IParamOptionService extends ISuperService<ParamOption> {
    int queryCountBySearch(ParamOptionSearch search);

    List<ParamOption> queryListBySearch(ParamOptionSearch search, PagerModel pagerModel);

    List<ParamOption> queryListByDefineName(String defineName);

    List<ParamOptionView> queryListByDefineNames(List<String> defineNames);

    Map<Object, List<ParamOption>> queryListGroupByDefineNames(List<String> defineNames);

    ParamOption queryModel(ParamOptionSearch search);

    ParamOption queryModel(String defineName,String text);

    ParamOption queryFirstOption(String defineName);

    boolean isMatch(String defineName,String value);

    String queryStrValue(String defineName);

    String queryStrValue(String defineName,String defaultValue);

    int queryIntValue(String defineName);

    int queryIntValue(String defineName,int defaultValue);

    double queryDoubleValue(String defineName);

    double queryDoubleValue(String defineName,double defaultValue);

    float queryFloatValue(String defineName);

    float queryFloatValue(String defineName,float defaultValue);
}
