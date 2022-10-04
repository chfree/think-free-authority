package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.viewmodel.RoleSearch;
import com.cditer.free.authority.data.entity.model.Role;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:05
 * @comment
 */

public interface IRoleService extends ISuperService<Role> {
    int queryCountBySearch(RoleSearch search);

    List<Role> queryListBySearch(RoleSearch search, PagerModel pagerModel);

    List<String> queryListByUserId(String userId);

    List<Role> queryListRoleByUserId(String userId);

    Role queryModelByRoleMark(String roleMark);

}
