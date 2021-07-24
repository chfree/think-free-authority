package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.model.Department;
import com.cditer.free.authority.data.entity.viewmodel.DepartmentSearch;
import com.cditer.free.authority.data.entity.viewmodel.DepartmentTree;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:05
 * @comment
 */

public interface IDepartmentService  extends ISuperService<Department> {
    int queryCountBySearch(DepartmentSearch search);

    List<Department> queryListBySearch(DepartmentSearch search, PagerModel pagerModel);

    List<DepartmentTree> queryListTreeBySearch(DepartmentSearch search);

    List<DepartmentTree> queryListTreeFormat(DepartmentSearch search);

    DepartmentTree queryModelTree(String id);
}
