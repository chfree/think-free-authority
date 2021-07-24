package com.cditer.free.authority.logical.dao.impl;

import com.cditer.free.authority.data.entity.viewmodel.RoleFuncSearch;
import com.cditer.free.authority.logical.dao.IRoleFuncDao;
import com.cditer.free.authority.data.entity.model.RoleFunc;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
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
