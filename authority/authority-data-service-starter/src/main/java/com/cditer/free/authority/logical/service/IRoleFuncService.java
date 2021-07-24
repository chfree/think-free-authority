package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.viewmodel.RoleFuncSearch;
import com.cditer.free.authority.data.entity.model.RoleFunc;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:06
 * @comment
 */

public interface IRoleFuncService extends ISuperService<RoleFunc> {
    boolean saveRoleFuncs(String roleId, List<RoleFunc> roleFuncs);

    boolean deleteByRoleId(String roleId);

    List<RoleFunc> queryListBySearch(RoleFuncSearch search);
}
