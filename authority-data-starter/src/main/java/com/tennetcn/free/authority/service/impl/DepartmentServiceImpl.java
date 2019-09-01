package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IDepartmentDao;
import com.tennetcn.free.authority.model.Department;
import com.tennetcn.free.authority.service.IDepartmentService;
import com.tennetcn.free.authority.viewmodel.DepartmentSearch;
import com.tennetcn.free.authority.viewmodel.DepartmentTree;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    IDepartmentDao departmentDao;

    @Override
    public int queryCountBySearch(DepartmentSearch search) {
        return departmentDao.queryCountBySearch(search);
    }

    @Override
    public List<Department> queryListBySearch(DepartmentSearch search, PagerModel pagerModel) {
        return departmentDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public List<DepartmentTree> queryListTreeBySearch(DepartmentSearch search) {
        return departmentDao.queryListTreeBySearch(search);
    }

    @Override
    public DepartmentTree queryModelTree(String id) {
        return departmentDao.queryModelTree(id);
    }

    @Override
    public List<DepartmentTree> queryListTreeFormat(DepartmentSearch search) {
        List<DepartmentTree> allDeptTrees = queryListTreeBySearch(search);
        if(allDeptTrees==null||allDeptTrees.size()<=0){
            return null;
        }
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


    private void deptTreeLoop(DepartmentTree currentDeptTree,List<DepartmentTree> allDeptTrees){

        var childrenDeptTree = allDeptTrees.stream().filter(dept-> currentDeptTree.getId().equals(dept.getParentId())).collect(Collectors.toList());

        currentDeptTree.setChildren(childrenDeptTree);
        for (DepartmentTree deptTree:childrenDeptTree) {
            deptTreeLoop(deptTree,allDeptTrees);
        }

    }
}
