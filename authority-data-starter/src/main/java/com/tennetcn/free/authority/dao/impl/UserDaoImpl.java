package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IUserDao;
import com.tennetcn.free.authority.model.Department;
import com.tennetcn.free.authority.model.User;
import com.tennetcn.free.authority.viewmodel.UserSearch;
import com.tennetcn.free.authority.viewmodel.UserView;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

@Component
public class UserDaoImpl extends SuperDao<User> implements IUserDao {

    @Override
    public int queryCountBySearch(UserSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(User.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<User> queryListBySearch(UserSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(User.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public List<UserView> queryViewListBySearch(UserSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(User.class,"user")
                     .setMainTableAlias("user")
                     .appendSelect("dept.full_name as departmentName")
                     .leftJoin(Department.class,"dept")
                     .on("user.department_id","dept.id");

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel,UserView.class);
    }

    @Override
    public User queryModelByLogin(String account, String password) {
        ISqlExpression sqlExpression=SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(User.class)
                     .andEq("account",account)
                     .andEq("password",password);

        return queryModel(sqlExpression);
    }

    @Override
    public User queryModelByAccount(String account) {
        ISqlExpression sqlExpression=SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(User.class)
                .andEq("account",account);

        return queryModel(sqlExpression);
    }

    @Override
    public UserView queryViewModelById(String id) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(User.class,"user")
                .setMainTableAlias("user")
                .appendSelect("dept.full_name as departmentName")
                .leftJoin(Department.class,"dept")
                .on("user.department_id","dept.id")
                .andEq("id",id);


        return queryModel(sqlExpression,UserView.class);
    }

    private void appendExpression(ISqlExpression sqlExpression, UserSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("account",search.getAccount());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andRightLikeNoEmpty("account",search.getLikeAccount());
    }
}
