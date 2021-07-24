package com.cditer.free.authority.logical.dao;

import com.cditer.free.authority.data.entity.viewmodel.RoleFuncSearch;
import com.cditer.free.authority.data.entity.model.RoleFunc;
import com.cditer.free.data.dao.base.ISuperDao;

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
