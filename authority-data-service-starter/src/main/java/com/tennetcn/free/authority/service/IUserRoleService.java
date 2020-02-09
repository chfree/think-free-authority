package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.data.model.Role;
import com.tennetcn.free.authority.data.model.UserRole;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:06
 * @comment
 */

public interface IUserRoleService extends ISuperService<UserRole> {
    boolean saveUserRoles(String userId,List<UserRole> userRoleList);

    boolean deleteByUserId(String userId);

    List<Role> queryListByUserId(String userId);
}
