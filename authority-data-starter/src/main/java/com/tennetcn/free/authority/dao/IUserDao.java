package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.model.User;
import com.tennetcn.free.authority.viewmodel.UserSearch;
import com.tennetcn.free.authority.viewmodel.UserView;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

public interface IUserDao extends ISuperDao<User> {
    int queryCountBySearch(UserSearch search);

    List<User> queryListBySearch(UserSearch search, PagerModel pagerModel);

    List<UserView> queryViewListBySearch(UserSearch search, PagerModel pagerModel);

    User queryModelByLogin(String account, String password);

    User queryModelByAccount(String account);

    UserView queryViewModelById(String id);
}
