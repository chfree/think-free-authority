package com.cditer.free.authority.logical.dao.impl;

import com.cditer.free.authority.entity.model.LoginUser;
import com.cditer.free.authority.entity.viewmodel.LoginUserSearch;
import com.cditer.free.authority.logical.dao.ILoginUserDao;
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
    public LoginUser queryModelByLogin(String account, String password) {
        ISqlExpression sqlExpression=SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(LoginUser.class)
                     .andEq("account",account)
                     .andEq("password",password);

        return queryModel(sqlExpression);
    }

    @Override
    public LoginUser queryModelByAccount(String account) {
        ISqlExpression sqlExpression=SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(LoginUser.class)
                .andEq("account",account);

        return queryModel(sqlExpression);
    }

    @Override
    public int queryCountByLoginUserSearch(LoginUserSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(LoginUser.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }


    private void appendExpression(ISqlExpression sqlExpression, LoginUserSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("account",search.getAccount());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andRightLikeNoEmpty("account",search.getLikeAccount());
    }
}
