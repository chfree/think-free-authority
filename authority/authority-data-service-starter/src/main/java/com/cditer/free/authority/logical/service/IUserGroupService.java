package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.model.Group;
import com.cditer.free.authority.data.entity.model.UserGroup;
import com.cditer.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:06
 * @comment
 */

public interface IUserGroupService extends ISuperService<UserGroup> {
    boolean saveUserGroups(String userId,List<UserGroup> userGroupList);

    boolean deleteByUserId(String userId);

    List<Group> queryListByUserId(String userId);
}
