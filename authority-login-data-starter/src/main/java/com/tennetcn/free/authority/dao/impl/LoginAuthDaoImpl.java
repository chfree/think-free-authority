package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.authority.dao.ILoginAuthDao;
import com.tennetcn.free.authority.model.LoginAuth;
import com.tennetcn.free.authority.viewmodel.LoginAuthSearch;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:32:34
 * @comment     登陆授权表
 */

@Component
public class LoginAuthDaoImpl extends SuperDao<LoginAuth> implements ILoginAuthDao{
    @Override
    public int queryCountBySearch(LoginAuthSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(LoginAuth.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<LoginAuth> queryListBySearch(LoginAuthSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(LoginAuth.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, LoginAuthSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("user_id",search.getUserId());

        sqlExpression.andEqNoEmpty("token",search.getToken());

        sqlExpression.andEqNoEmpty("type",search.getType());

        sqlExpression.andEqNoEmpty("status",search.getStatus());

    }
}