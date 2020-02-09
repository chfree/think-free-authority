package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IRoleDao;
import com.tennetcn.free.authority.data.model.Role;
import com.tennetcn.free.authority.data.viewmodel.RoleSearch;
import com.tennetcn.free.authority.data.model.UserRole;
import com.tennetcn.free.core.enums.OrderEnum;
import com.tennetcn.free.core.message.data.PagerModel;
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
public class RoleDaoImpl extends SuperDao<Role> implements IRoleDao {
    @Override
    public int queryCountBySearch(RoleSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(Role.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<Role> queryListBySearch(RoleSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Role.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public List<String> queryListByUserId(String userId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.select("role_id")
                .from(UserRole.class)
                .andEq("user_id",userId);

        return  queryList(sqlExpression,String.class);
    }

    @Override
    public List<Role> queryListRoleByUserId(String userId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.select("role.id,role.comments,role.delete_mark,role.description,role.mark_code,role.role_name,role.sort_code,role.role_mark")
                .from(UserRole.class,"userRole")
                .leftJoin(Role.class,"role").on("role.id","userRole.role_id")
                .andEq("userRole.user_id",userId)
                .addOrder("role.sort_code", OrderEnum.asc);

        return  queryList(sqlExpression,Role.class);
    }

    private void appendExpression(ISqlExpression sqlExpression, RoleSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("role_name",search.getRoleName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("role_name",search.getLikeRoleName());
    }
}
