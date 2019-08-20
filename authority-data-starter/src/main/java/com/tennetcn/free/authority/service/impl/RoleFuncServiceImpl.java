package com.tennetcn.free.authority.service.impl;

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
    public boolean saveRoleFuncs(String userId, List<RoleFunc> roleFuncs) {
        if(!deleteByUserId(userId)){
            return false;
        }
        if(roleFuncs==null||roleFuncs.size()<=0){
            return true;
        }

        return insertListEx(roleFuncs)==roleFuncs.size();
    }

    @Override
    public boolean deleteByUserId(String userId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.delete()
                .from(RoleFunc.class)
                .andEq("user_id",userId);

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
        sqlExpression.andEqNoEmpty("user_id",search.getUserId());
    }
}
