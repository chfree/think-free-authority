package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.viewmodel.GroupSearch;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.authority.data.entity.model.Group;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-05-31 12:49:41
 * @comment     权限组
 */

public interface IGroupService extends ISuperService<Group>{
    int queryCountBySearch(GroupSearch search);

    List<Group> queryListBySearch(GroupSearch search, PagerModel pagerModel);

    List<Group> queryListByUserId(String userId);
}
