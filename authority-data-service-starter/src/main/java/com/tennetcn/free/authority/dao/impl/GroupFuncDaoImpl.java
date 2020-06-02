package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IGroupFuncDao;
import com.tennetcn.free.authority.data.entity.model.GroupFunc;
import com.tennetcn.free.authority.data.entity.model.RoleFunc;
import com.tennetcn.free.authority.data.entity.viewmodel.GroupFuncSearch;
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
public class GroupFuncDaoImpl extends SuperDao<GroupFunc> implements IGroupFuncDao {

    @Override
    public boolean deleteByGroupId(String groupId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.delete()
                .from(GroupFunc.class)
                .andEq("group_id",groupId);

        return delete(sqlExpression)>=0;
    }

    @Override
    public List<GroupFunc> queryListBySearch(GroupFuncSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(RoleFunc.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression);
    }

    private void appendExpression(ISqlExpression sqlExpression,GroupFuncSearch search){
        sqlExpression.andEqNoEmpty("group_id",search.getGroupId());
    }
}
