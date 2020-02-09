package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.data.model.RoleFunc;
import com.tennetcn.free.authority.data.viewmodel.RoleFuncSearch;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:06
 * @comment
 */

public interface IRoleFuncDao extends ISuperDao<RoleFunc> {
    boolean deleteByRoleId(String roleId);

    List<RoleFunc> queryListBySearch(RoleFuncSearch search);
}
