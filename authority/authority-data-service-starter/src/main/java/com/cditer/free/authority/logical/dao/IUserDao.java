package com.cditer.free.authority.logical.dao;

import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.authority.data.entity.viewmodel.UserSearch;
import com.cditer.free.authority.data.entity.viewmodel.UserView;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

public interface IUserDao extends ISuperDao<User> {
    int queryCountBySearch(UserSearch search);

    User queryModelBySearch(UserSearch search);

    List<User> queryListBySearch(UserSearch search, PagerModel pagerModel);

    List<UserView> queryViewListBySearch(UserSearch search, PagerModel pagerModel);

    User queryModelByLogin(String account, String password);

    User queryModelByAccount(String account);

    UserView queryViewModelById(String id);

    int queryCountByLoginUserSearch(UserSearch search);
}
