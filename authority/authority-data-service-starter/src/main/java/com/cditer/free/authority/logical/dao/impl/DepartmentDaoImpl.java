package com.cditer.free.authority.logical.dao.impl;

import com.cditer.free.authority.data.entity.viewmodel.DepartmentSearch;
import com.cditer.free.authority.data.entity.viewmodel.DepartmentTree;
import com.cditer.free.authority.logical.dao.IDepartmentDao;
import com.cditer.free.authority.data.entity.model.Department;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:13
 * @comment
 */

@Component
public class DepartmentDaoImpl extends SuperDao<Department> implements IDepartmentDao {
    @Override
    public int queryCountBySearch(DepartmentSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(Department.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<Department> queryListBySearch(DepartmentSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Department.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public List<DepartmentTree> queryListTreeBySearch(DepartmentSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Department.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,DepartmentTree.class);
    }

    @Override
    public DepartmentTree queryModelTree(String id) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Department.class,"dept")
                     .appendSelect("parent.full_name as parentName")
                     .leftJoin(Department.class,"parent")
                     .on("dept.parent_id","parent.id")
                     .andEq("dept.id",id);

        return queryModel(sqlExpression, DepartmentTree.class);
    }

    private void appendExpression(ISqlExpression sqlExpression, DepartmentSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("full_name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("full_name",search.getLikeName());
    }
}
