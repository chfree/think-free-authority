package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.model.ParamOption;
import com.tennetcn.free.authority.viewmodel.ParamOptionSearch;
import com.tennetcn.free.authority.viewmodel.ParamOptionView;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.data.message.PagerModel;

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
}
