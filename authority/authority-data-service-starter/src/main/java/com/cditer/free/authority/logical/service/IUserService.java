package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.apimodel.user.SaveUserReq;
import com.cditer.free.authority.data.entity.viewmodel.UserSearch;
import com.cditer.free.authority.data.entity.viewmodel.UserView;
import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;

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

    List<UserView> queryViewListBySearch(UserSearch search, PagerModel pagerModel);

    UserView queryViewModelById(String id);

    String passwordFormat(String password);

    boolean saveUser(SaveUserReq saveUserReq);
}
