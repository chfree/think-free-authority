package com.cditer.free.login.service.logical.dao.impl;

import com.cditer.free.login.service.logical.entity.model.LoginUser;
import com.cditer.free.login.service.logical.entity.viewmodel.LoginUserSearch;
import com.cditer.free.login.service.logical.dao.ILoginUserDao;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

@Component
public class LoginUserDaoImpl extends SuperDao<LoginUser> implements ILoginUserDao {

    @Override
    public int queryCountBySearch(LoginUserSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(LoginUser.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public LoginUser queryModelBySearch(LoginUserSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(LoginUser.class);

        appendExpression(sqlExpression,search);

        return queryModel(sqlExpression);
    }

    @Override
    public LoginUser queryModelByLogin(String account, String password) {
        ISqlExpression sqlExpression=SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(LoginUser.class)
                .andEq("account",account)
                .andEq("password",password);

        return queryModel(sqlExpression);
    }

    private void appendExpression(ISqlExpression sqlExpression, LoginUserSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("account",search.getAccount());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andRightLikeNoEmpty("account",search.getLikeAccount());

        sqlExpression.andEqNoEmpty("status",search.getStatus());
    }
}
