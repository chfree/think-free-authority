package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.authority.data.entity.viewmodel.LoginUserSearch;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

public interface ILoginUserService{
    User queryModelByLogin(String account, String password);

    User queryModelByAccount(String account);

    int queryCountByLoginUserSearch(LoginUserSearch search);

    String passwordFormat(String password);

    boolean applyChange(User user);
}
