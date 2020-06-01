package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IGroupDao;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.authority.data.entity.model.Group;
import com.tennetcn.free.authority.data.entity.viewmodel.GroupSearch;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-05-31 12:47:27
 * @comment     权限组
 */

@Component
public class GroupDaoImpl extends SuperDao<Group> implements IGroupDao {
    @Override
    public int queryCountBySearch(GroupSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(Group.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<Group> queryListBySearch(GroupSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Group.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, GroupSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("desc",search.getDesc());

        sqlExpression.andEqNoEmpty("group_mark",search.getGroupMark());

        sqlExpression.andEqNoEmpty("level",search.getLevel());

        sqlExpression.andEqNoEmpty("status",search.getStatus());
    }
}
