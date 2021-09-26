package com.cditer.free.login.service.logical.service;

import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.login.service.logical.entity.model.LoginUser;
import com.cditer.free.login.service.logical.entity.viewmodel.LoginUserSearch;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

public interface ILoginUserService extends ISuperService<LoginUser> {
    int queryCountBySearch(LoginUserSearch search);

    LoginUser queryModelBySearch(LoginUserSearch search);

    LoginUser queryModelBySearch(String userId);

    LoginUser queryModelByLogin(String account, String password);

    String passwordFormat(String password);
}
