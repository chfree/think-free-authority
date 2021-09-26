package com.cditer.free.login.service.logical.dao.impl;

import com.cditer.free.core.enums.OrderEnum;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.login.entity.model.LoginAuthBase;
import com.cditer.free.login.entity.model.LoginUser;
import com.cditer.free.login.entity.viewmodel.LoginAuthSearch;
import com.cditer.free.login.entity.viewmodel.LoginAuthBaseView;
import com.cditer.free.login.enums.LoginAuthStatus;
import com.cditer.free.login.service.logical.dao.ILoginAuthBaseDao;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:32:34
 * @comment     登陆授权表
 */

@Component
public class LoginAuthBaseDaoImpl extends SuperDao<LoginAuthBase> implements ILoginAuthBaseDao {

    private String mainTableAlias = "loginAuth";

    @Override
    public int queryCountBySearch(LoginAuthSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();

        sqlExpression.selectCount().from(LoginAuthBase.class,mainTableAlias)
                .leftJoin(LoginUser.class,"user").on(mainTableAlias+".user_id","user.id");


        appendExpression(sqlExpression,search);
        appendUserWhere(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<LoginAuthBaseView> queryListBySearch(LoginAuthSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();

        sqlExpression.selectAllFrom(LoginAuthBase.class, mainTableAlias)
                .appendSelect("user.account as account", "user.name as name")
                .leftJoin(LoginUser.class, "user").on(mainTableAlias + ".user_id", "user.id")
                .addOrder("auth_tm", OrderEnum.desc);


        appendExpression(sqlExpression,search);
        appendUserWhere(sqlExpression,search);

        return queryList(sqlExpression,pagerModel, LoginAuthBaseView.class);
    }

    @Override
    public boolean updateStatusByUserId(String userId,String status) {
        String notStatus = LoginAuthStatus.VALID.getValue().equals(status)? LoginAuthStatus.INVALID.getValue():LoginAuthStatus.VALID.getValue();

        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.update(LoginAuthBase.class)
                     .set("status","setStatus")
                     .setParam("setStatus",status)
                     .andEq("user_id",userId)
                     .andEq("status",notStatus);
        
        return update(sqlExpression)>=0;
    }

    @Override
    public boolean checkTokenIsValid(String token) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();

        sqlExpression.selectCount()
                .from(LoginAuthBase.class)
                .andEq("token", token)
                .andEq("status", LoginAuthStatus.VALID.getValue())
                .andWhere("exp_tm>now()");

        return queryCount(sqlExpression) > 0;
    }

    @Override
    public boolean updateStatusByToken(String token, String status) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.update(LoginAuthBase.class)
                .setColumn("status",status)
                .andEq("token",token);

        return update(sqlExpression)>=0;
    }

    private void appendUserWhere(ISqlExpression sqlExpression,LoginAuthSearch search){
        sqlExpression.andLikeNoEmpty("user.account", search.getAccount());

        sqlExpression.andLikeNoEmpty("user.name", search.getName());
    }

    private void appendExpression(ISqlExpression sqlExpression, LoginAuthSearch search){
        sqlExpression.andEqNoEmpty("id", search.getId());

        sqlExpression.andNotEqNoEmpty("id", search.getNotId());

        sqlExpression.andEqNoEmpty("user_id", search.getUserId());

        sqlExpression.andEqNoEmpty("token", search.getToken());

        sqlExpression.andEqNoEmpty("type", search.getType());

        sqlExpression.andEqNoEmpty("status", search.getStatus());

    }
}