package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.data.model.Department;
import com.tennetcn.free.authority.data.viewmodel.DepartmentSearch;
import com.tennetcn.free.authority.data.viewmodel.DepartmentTree;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:05
 * @comment
 */

public interface IDepartmentDao extends ISuperDao<Department> {
    int queryCountBySearch(DepartmentSearch search);

    List<Department> queryListBySearch(DepartmentSearch search, PagerModel pagerModel);

    List<DepartmentTree> queryListTreeBySearch(DepartmentSearch search);

    DepartmentTree queryModelTree(String id);
}
