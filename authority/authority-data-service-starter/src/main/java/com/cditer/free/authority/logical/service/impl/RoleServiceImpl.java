package com.cditer.free.authority.logical.service.impl;

import com.cditer.free.authority.logical.dao.IRoleDao;
import com.cditer.free.authority.data.entity.model.Role;
import com.cditer.free.authority.logical.service.IRoleService;
import com.cditer.free.authority.data.entity.viewmodel.RoleSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
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
