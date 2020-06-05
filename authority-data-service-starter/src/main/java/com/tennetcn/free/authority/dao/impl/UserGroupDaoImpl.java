package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IUserGroupDao;
import com.tennetcn.free.authority.data.entity.model.Group;
import com.tennetcn.free.authority.data.entity.model.UserGroup;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:14
 * @comment
 */

@Component
public class UserGroupDaoImpl extends SuperDao<UserGroup> implements IUserGroupDao {
    @Override
    public boolean deleteByUserId(String userId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.delete()
                     .from(UserGroup.class)
                     .andEq("user_id",userId);

        return delete(sqlExpression) >= 0;
    }

    @Override
    public List<Group> queryListByUserId(String userId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.select("gp.id,gp.name")
                     .from(UserGroup.class,"ur")
                     .leftJoin(Group.class,"gp").on("ur.group_id","gp.id")
                     .andEq("ur.user_id",userId);

        return queryList(sqlExpression,Group.class);
    }
}
