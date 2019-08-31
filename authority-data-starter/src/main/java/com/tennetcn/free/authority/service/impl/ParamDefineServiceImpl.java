package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IParamDefineDao;
import com.tennetcn.free.authority.model.ParamDefine;
import com.tennetcn.free.authority.service.IParamDefineService;
import com.tennetcn.free.authority.viewmodel.ParamDefineSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.enums.OrderEnum;
import com.tennetcn.free.data.message.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
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
