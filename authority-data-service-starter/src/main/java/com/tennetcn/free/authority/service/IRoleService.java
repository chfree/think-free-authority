package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.data.model.Role;
import com.tennetcn.free.authority.data.viewmodel.RoleSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;

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
}
