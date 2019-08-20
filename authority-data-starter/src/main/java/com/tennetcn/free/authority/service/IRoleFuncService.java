package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.model.RoleFunc;
import com.tennetcn.free.authority.model.UserRole;
import com.tennetcn.free.authority.viewmodel.MenuButtonTree;
import com.tennetcn.free.authority.viewmodel.RoleFuncSearch;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:06
 * @comment
 */

public interface IRoleFuncService extends ISuperService<RoleFunc> {
    boolean saveRoleFuncs(String userId, List<RoleFunc> roleFuncs);

    boolean deleteByUserId(String userId);

    List<RoleFunc> queryListBySearch(RoleFuncSearch search);
}
