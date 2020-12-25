package com.tennetcn.free.authority.logical.service;

import com.tennetcn.free.authority.entity.model.LoginUser;
import com.tennetcn.free.authority.entity.viewmodel.LoginUserSearch;
import com.tennetcn.free.data.dao.base.ISuperService;

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
