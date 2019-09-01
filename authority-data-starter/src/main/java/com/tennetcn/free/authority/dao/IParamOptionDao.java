package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.model.ParamOption;
import com.tennetcn.free.authority.viewmodel.ParamOptionSearch;
import com.tennetcn.free.authority.viewmodel.ParamOptionView;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IParamOptionDao extends ISuperDao<ParamOption> {
    int queryCountBySearch(ParamOptionSearch search);

    List<ParamOption> queryListBySearch(ParamOptionSearch search, PagerModel pagerModel);

    List<ParamOption> queryListByDefineName(String defineName);

    List<ParamOptionView> queryListByDefineNames(List<String> defineNames);
}