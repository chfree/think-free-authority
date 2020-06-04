package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.data.entity.apimodel.user.SaveUserReq;
import com.tennetcn.free.authority.data.entity.apimodel.user.UpdatePwd;
import com.tennetcn.free.authority.data.entity.model.User;
import com.tennetcn.free.authority.data.entity.viewmodel.UserSearch;
import com.tennetcn.free.authority.data.entity.viewmodel.UserView;
import com.tennetcn.free.core.message.data.PagerModel;
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

    List<UserView> queryViewListBySearch(UserSearch search, PagerModel pagerModel);

    UserView queryViewModelById(String id);

    String passwordFormat(String password);

    boolean saveUser(SaveUserReq saveUserReq);
}
