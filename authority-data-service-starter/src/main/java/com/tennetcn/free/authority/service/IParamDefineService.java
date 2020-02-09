package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.data.entity.model.ParamDefine;
import com.tennetcn.free.authority.data.entity.viewmodel.ParamDefineSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IParamDefineService extends ISuperService<ParamDefine> {
    int queryCountBySearch(ParamDefineSearch search);

    List<ParamDefine> queryListBySearch(ParamDefineSearch search, PagerModel pagerModel);
}
