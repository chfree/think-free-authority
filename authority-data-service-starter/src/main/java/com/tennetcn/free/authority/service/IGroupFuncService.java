package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.data.entity.model.GroupFunc;
import com.tennetcn.free.authority.data.entity.viewmodel.GroupFuncSearch;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:06
 * @comment
 */

public interface IGroupFuncService extends ISuperService<GroupFunc> {
    boolean saveGroupFuncs(String groupId, List<GroupFunc> groupFuncs);

    boolean deleteByGroupId(String groupId);

    List<GroupFunc> queryListBySearch(GroupFuncSearch search);
}
