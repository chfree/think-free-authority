package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IRoleFuncDao;
import com.tennetcn.free.authority.data.model.RoleFunc;
import com.tennetcn.free.authority.data.viewmodel.RoleFuncSearch;
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
public class RoleFuncDaoImpl extends SuperDao<RoleFunc> implements IRoleFuncDao {

    @Override
    public boolean deleteByRoleId(String roleId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.delete()
                .from(RoleFunc.class)
                .andEq("role_id",roleId);

        return delete(sqlExpression)>=0;
    }

    @Override
    public List<RoleFunc> queryListBySearch(RoleFuncSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(RoleFunc.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression);
    }

    private void appendExpression(ISqlExpression sqlExpression,RoleFuncSearch search){
        sqlExpression.andEqNoEmpty("role_id",search.getRoleId());
    }
}
