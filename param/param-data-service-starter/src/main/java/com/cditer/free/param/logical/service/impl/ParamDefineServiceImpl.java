package com.cditer.free.param.logical.service.impl;

import com.cditer.free.param.logical.dao.IParamDefineDao;
import com.cditer.free.param.data.entity.model.ParamDefine;
import com.cditer.free.param.data.entity.viewmodel.ParamDefineSearch;
import com.cditer.free.param.logical.service.IParamDefineService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:13
 * @comment
 */

@Component
public class ParamDefineServiceImpl extends SuperService<ParamDefine> implements IParamDefineService {

    @Autowired
    IParamDefineDao paramDefineDao;

    @Override
    public int queryCountBySearch(ParamDefineSearch search) {
        return paramDefineDao.queryCountBySearch(search);
    }

    @Override
    public List<ParamDefine> queryListBySearch(ParamDefineSearch search, PagerModel pagerModel) {
        return paramDefineDao.queryListBySearch(search,pagerModel);
    }
}
