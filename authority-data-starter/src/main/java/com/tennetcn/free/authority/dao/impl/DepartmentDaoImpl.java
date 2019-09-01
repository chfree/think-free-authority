package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IDepartmentDao;
import com.tennetcn.free.authority.model.Department;
import com.tennetcn.free.authority.viewmodel.DepartmentSearch;
import com.tennetcn.free.authority.viewmodel.DepartmentTree;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
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
