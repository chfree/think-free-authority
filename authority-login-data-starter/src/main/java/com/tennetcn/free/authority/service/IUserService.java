package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.model.User;
import com.tennetcn.free.authority.viewmodel.UserSearch;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

public interface IUserService extends ISuperService<User> {
    int queryCountBySearch(UserSearch search);

    List<User> queryListBySearch(UserSearch search, PagerModel pagerModel);

    User queryModelByLogin(String account, String password);

    User queryModelByAccount(String account);
}
