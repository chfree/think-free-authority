package com.tennetcn.free.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.authority.model.Department;
import com.tennetcn.free.authority.service.IDepartmentService;
import com.tennetcn.free.authority.viewmodel.DepartmentSearch;
import com.tennetcn.free.authority.viewmodel.DepartmentTree;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.message.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:13
 * @comment
 */

@Component
public class DepartmentServiceImpl extends SuperService<Department> implements IDepartmentService {
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
    public List<DepartmentTree> queryListTreeFormat(DepartmentSearch search) {
        List<DepartmentTree> allDeptTrees = queryListTreeBySearch(search);

        // 求出最顶级的level是多少
        // 默认顶级是0，考虑到搜索的情况，这里在求一下
        Integer minLevel = allDeptTrees.stream().min(Comparator.comparing(Department::getLevel)).get().getLevel();

        // 在求出顶级所有的顶级部门
        var minDeptTrees = allDeptTrees.stream().filter(dept -> dept.getLevel() == minLevel).collect(Collectors.toList());

        // 进行递归循环
        for (DepartmentTree deptTree: minDeptTrees) {
            deptTreeLoop(deptTree,allDeptTrees);
        }

        return minDeptTrees;
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

    private void deptTreeLoop(DepartmentTree currentDeptTree,List<DepartmentTree> allDeptTrees){

        var childrenDeptTree = allDeptTrees.stream().filter(dept-> currentDeptTree.getId().equals(dept.getParentId())).collect(Collectors.toList());

        currentDeptTree.setChildren(childrenDeptTree);
        for (DepartmentTree deptTree:childrenDeptTree) {
            deptTreeLoop(deptTree,allDeptTrees);
        }

    }

    private void appendExpression(ISqlExpression sqlExpression, DepartmentSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("full_name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("full_name",search.getLikeName());
    }
}
