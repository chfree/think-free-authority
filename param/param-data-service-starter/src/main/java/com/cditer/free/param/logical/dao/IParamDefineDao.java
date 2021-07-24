package com.cditer.free.param.logical.dao;

import com.cditer.free.param.data.entity.model.ParamDefine;
import com.cditer.free.param.data.entity.viewmodel.ParamDefineSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IParamDefineDao extends ISuperDao<ParamDefine> {
    int queryCountBySearch(ParamDefineSearch search);

    List<ParamDefine> queryListBySearch(ParamDefineSearch search, PagerModel pagerModel);
}
