package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IUserGroupDao;
import com.tennetcn.free.authority.data.entity.model.Group;
import com.tennetcn.free.authority.data.entity.model.UserGroup;
import com.tennetcn.free.authority.service.IUserGroupService;
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
public class UserGroupServiceImpl extends SuperService<UserGroup> implements IUserGroupService {

    @Autowired
    IUserGroupDao userGroupDao;

    @Override
    public boolean saveUserGroups(String userId,List<UserGroup> userGroupList) {
        if(!deleteByUserId(userId)){
            return false;
        }
        if(userGroupList==null||userGroupList.size()<=0){
            return true;
        }
        return insertListEx(userGroupList) == userGroupList.size();
    }

    @Override
    public boolean deleteByUserId(String userId) {
        return userGroupDao.deleteByUserId(userId);
    }

    @Override
    public List<Group> queryListByUserId(String userId) {
        return userGroupDao.queryListByUserId(userId);
    }
}
