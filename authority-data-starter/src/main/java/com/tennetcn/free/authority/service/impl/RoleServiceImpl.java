package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IRoleDao;
import com.tennetcn.free.authority.model.Role;
import com.tennetcn.free.authority.service.IRoleService;
import com.tennetcn.free.authority.viewmodel.RoleSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:14
 * @comment
 */

@Component
public class RoleServiceImpl extends SuperService<Role> implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Override
    public int queryCountBySearch(RoleSearch search) {
        return roleDao.queryCountBySearch(search);
    }

    @Override
    public List<Role> queryListBySearch(RoleSearch search, PagerModel pagerModel) {
        return roleDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public List<String> queryListByUserId(String userId) {
        return roleDao.queryListByUserId(userId);
    }

    @Override
    public List<Role> queryListRoleByUserId(String userId) {
        return roleDao.queryListRoleByUserId(userId);
    }
}
