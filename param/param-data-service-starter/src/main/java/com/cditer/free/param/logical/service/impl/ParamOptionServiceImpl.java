package com.cditer.free.param.logical.service.impl;

import com.cditer.free.param.logical.dao.IParamOptionDao;
import com.cditer.free.param.data.entity.model.ParamOption;
import com.cditer.free.param.data.entity.viewmodel.ParamOptionSearch;
import com.cditer.free.param.data.entity.viewmodel.ParamOptionView;
import com.cditer.free.param.logical.service.IParamOptionService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:13
 * @comment
 */

@Component
public class ParamOptionServiceImpl extends SuperService<ParamOption> implements IParamOptionService {

    @Autowired
    IParamOptionDao paramOptionDao;

    @Override
    public int queryCountBySearch(ParamOptionSearch search) {
        return paramOptionDao.queryCountBySearch(search);
    }

    @Override
    public List<ParamOption> queryListBySearch(ParamOptionSearch search, PagerModel pagerModel) {
        return paramOptionDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public List<ParamOption> queryListByDefineName(String defineName) {
        return paramOptionDao.queryListByDefineName(defineName);
    }

    @Override
    public List<ParamOptionView> queryListByDefineNames(List<String> defineNames) {
        return paramOptionDao.queryListByDefineNames(defineNames);
    }

    @Override
    public Map<Object, List<ParamOption>> queryListGroupByDefineNames(List<String> defineNames) {
        if(defineNames == null || defineNames.size()<=0){
            return null;
        }

        List<ParamOptionView> paramOptionViews = queryListByDefineNames(defineNames);
        // 进行分组
        Map<Object, List<ParamOption>> resultMap = new HashMap<>();
        for (ParamOptionView paramOptionView : paramOptionViews) {
            resultMap.computeIfAbsent(paramOptionView.getDefineName(), k -> new ArrayList<>()).add(paramOptionView);
        }

        return resultMap;
    }

    @Override
    public ParamOption queryModel(ParamOptionSearch search) {
        return paramOptionDao.queryModel(search);
    }

    @Override
    public ParamOption queryModel(String defineName, String text) {
        ParamOptionSearch search = new ParamOptionSearch();
        search.setDefineName(defineName);
        search.setText(text);

        return queryModel(search);
    }

    @Override
    public ParamOption queryFirstOption(String defineName) {
        List<ParamOption> paramOptions = queryListByDefineName(defineName);
        if(paramOptions==null||paramOptions.isEmpty()){
            return null;
        }
        return paramOptions.get(0);
    }

    @Override
    public boolean isMatch(String defineName, String value) {
        ParamOption paramOption = queryFirstOption(defineName);
        if(paramOption==null){
            return false;
        }
        return value.equals(paramOption.getValue());
    }

    @Override
    public String queryStrValue(String defineName) {
        ParamOption paramOption = queryFirstOption(defineName);
        if(paramOption==null){
            return null;
        }
        return paramOption.getValue();
    }

    @Override
    public String queryStrValue(String defineName, String defaultValue) {
        ParamOption paramOption = queryFirstOption(defineName);
        if(paramOption==null){
            return defaultValue;
        }
        if(StringUtils.isEmpty(paramOption.getValue())){
            return defaultValue;
        }
        return paramOption.getValue();
    }

    @Override
    public int queryIntValue(String defineName) {
        return queryIntValue(defineName,0);
    }

    @Override
    public int queryIntValue(String defineName, int defaultValue) {
        ParamOption paramOption = queryFirstOption(defineName);
        if(paramOption==null){
            return defaultValue;
        }
        return paramOption.getIntValue(defaultValue);
    }

    @Override
    public double queryDoubleValue(String defineName) {
        return queryDoubleValue(defineName,0);
    }

    @Override
    public double queryDoubleValue(String defineName, double defaultValue) {
        ParamOption paramOption = queryFirstOption(defineName);
        if(paramOption==null){
            return defaultValue;
        }
        return paramOption.getDoubleValue(defaultValue);
    }

    @Override
    public float queryFloatValue(String defineName) {
        return queryFloatValue(defineName);
    }

    @Override
    public float queryFloatValue(String defineName, float defaultValue) {
        ParamOption paramOption = queryFirstOption(defineName);
        if(paramOption==null){
            return defaultValue;
        }
        return paramOption.getFloatValue(defaultValue);
    }
}
