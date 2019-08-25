package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.model.Business;
import com.tennetcn.free.authority.model.Role;
import com.tennetcn.free.authority.model.UserRole;
import com.tennetcn.free.authority.service.IRoleService;
import com.tennetcn.free.authority.viewmodel.RoleSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.message.PagerModel;
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
public class RoleServiceImpl extends SuperService<Role> implements IRoleService {
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

    private void appendExpression(ISqlExpression sqlExpression, RoleSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("role_name",search.getRoleName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("role_name",search.getLikeRoleName());
    }
}
