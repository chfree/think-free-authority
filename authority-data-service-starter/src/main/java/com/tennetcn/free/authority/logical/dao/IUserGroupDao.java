package com.tennetcn.free.authority.logical.dao;

import com.tennetcn.free.authority.data.entity.model.Group;
import com.tennetcn.free.authority.data.entity.model.UserGroup;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:06
 * @comment
 */

public interface IUserGroupDao extends ISuperDao<UserGroup> {
    boolean deleteByUserId(String userId);

    List<Group> queryListByUserId(String userId);
}
