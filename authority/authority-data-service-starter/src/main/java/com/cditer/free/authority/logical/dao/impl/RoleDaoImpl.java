package com.cditer.free.authority.logical.dao.impl;

import com.cditer.free.authority.data.entity.viewmodel.RoleSearch;
import com.cditer.free.authority.logical.dao.IRoleDao;
import com.cditer.free.authority.data.entity.model.Role;
import com.cditer.free.authority.data.entity.model.UserRole;
import com.cditer.free.core.enums.OrderEnum;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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

        appendExpression(sqlExpression, search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<Role> queryListBySearch(RoleSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Role.class);

        appendExpression(sqlExpression, search);

        return queryList(sqlExpression, pagerModel);
    }

    @Override
    public List<String> queryListByUserId(String userId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(UserRole.class)
                .andEq("user_id", userId);

        List<UserRole> userRoles = queryList(sqlExpression, UserRole.class);
        if (CollectionUtils.isEmpty(userRoles)) {
            return null;
        }
        return userRoles.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
    }

    @Override
    public List<Role> queryListRoleByUserId(String userId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.select("role.id,role.comments,role.delete_mark,role.description,role.mark_code,role.role_name,role.sort_code,role.role_mark")
                .from(UserRole.class, "userRole")
                .leftJoin(Role.class, "role").on("role.id", "userRole.role_id")
                .andEq("userRole.user_id", userId)
                .addOrder("role.sort_code", OrderEnum.ASC);

        return queryList(sqlExpression, Role.class);
    }

    private void appendExpression(ISqlExpression sqlExpression, RoleSearch search) {
        sqlExpression.andEqNoEmpty("id", search.getId());

        sqlExpression.andEqNoEmpty("role_name", search.getRoleName());

        sqlExpression.andNotEqNoEmpty("id", search.getNotId());

        sqlExpression.andRightLikeNoEmpty("role_name", search.getLikeRoleName());
    }
}
