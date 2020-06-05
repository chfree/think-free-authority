package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.data.entity.model.GroupFunc;
import com.tennetcn.free.authority.data.entity.viewmodel.GroupFuncSearch;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:06
 * @comment
 */

public interface IGroupFuncDao extends ISuperDao<GroupFunc> {
    boolean deleteByGroupId(String groupId);

    List<GroupFunc> queryListBySearch(GroupFuncSearch search);
}
