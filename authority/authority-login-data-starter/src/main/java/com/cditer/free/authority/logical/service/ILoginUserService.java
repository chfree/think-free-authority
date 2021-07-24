package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.entity.model.LoginUser;
import com.cditer.free.authority.entity.viewmodel.LoginUserSearch;
import com.cditer.free.data.dao.base.ISuperService;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

public interface ILoginUserService extends ISuperService<LoginUser> {
    LoginUser queryModelByLogin(String account, String password);

    LoginUser queryModelByAccount(String account);

    int queryCountByLoginUserSearch(LoginUserSearch search);

    String passwordFormat(String password);
}
