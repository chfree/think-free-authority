package com.cditer.free.login.service.logical.dao;

import com.cditer.free.login.entity.model.LoginUser;
import com.cditer.free.login.entity.viewmodel.LoginUserSearch;
import com.cditer.free.data.dao.base.ISuperDao;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

public interface ILoginUserDao extends ISuperDao<LoginUser> {
    int queryCountBySearch(LoginUserSearch search);

    LoginUser queryModelBySearch(LoginUserSearch search);

    LoginUser queryModelByLogin(String account, String password);
}
