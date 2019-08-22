package com.tennetcn.free.authority.service.impl;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.model.RoleFunc;
import com.tennetcn.free.authority.service.IRoleFuncService;
import com.tennetcn.free.authority.viewmodel.RoleFuncSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
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
public class RoleFuncServiceImpl extends SuperService<RoleFunc> implements IRoleFuncService {

    @Override
    public boolean saveRoleFuncs(String roleId, List<RoleFunc> roleFuncs) {
        if(!deleteByRoleId(roleId)){
            return false;
        }
        if(roleFuncs==null||roleFuncs.size()<=0){
            return true;
        }
        roleFuncs.forEach(roleFunc -> {
            roleFunc.setId(IdUtil.randomUUID());
            roleFunc.setRoleId(roleId);
        });

        return insertListEx(roleFuncs)==roleFuncs.size();
    }

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
