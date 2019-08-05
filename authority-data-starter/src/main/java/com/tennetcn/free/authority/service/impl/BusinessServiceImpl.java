package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.model.Business;
import com.tennetcn.free.authority.service.IBusinessService;
import com.tennetcn.free.authority.viewmodel.BusinessSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:12
 * @comment
 */

@Component
public class BusinessServiceImpl extends SuperService<Business> implements IBusinessService {
    @Override
    public int queryCountBySearch(BusinessSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(Business.class);

        if(!StringUtils.isEmpty(search.getId())){
            sqlExpression.andEq("id",search.getId());
        }

        if(!StringUtils.isEmpty(search.getName())){
            sqlExpression.andEq("name",search.getName());
        }

        if(!StringUtils.isEmpty(search.getNotId())){
            sqlExpression.andNotEq("id",search.getNotId());
        }

        return queryCount(sqlExpression);
    }
}
