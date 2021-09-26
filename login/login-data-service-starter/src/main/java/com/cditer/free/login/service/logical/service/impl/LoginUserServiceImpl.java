package com.cditer.free.login.service.logical.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.cditer.free.login.service.logical.service.ILoginUserService;
import com.cditer.free.login.service.logical.dao.ILoginUserDao;
import com.cditer.free.login.service.logical.entity.model.LoginUser;
import com.cditer.free.login.service.logical.entity.viewmodel.LoginUserSearch;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

@Component
public class LoginUserServiceImpl extends SuperService<LoginUser> implements ILoginUserService {

    @Autowired
    ILoginUserDao userDao;

    @Override
    public int queryCountBySearch(LoginUserSearch search) {
        return userDao.queryCountBySearch(search);
    }

    @Override
    public LoginUser queryModelBySearch(LoginUserSearch search) {
        return userDao.queryModelBySearch(search);
    }

    @Override
    public LoginUser queryModelBySearch(String userId) {
        LoginUserSearch userSearch = new LoginUserSearch();
        userSearch.setId(userId);

        return queryModelBySearch(userSearch);
    }

    @Override
    public LoginUser queryModelByLogin(String account, String password) {
        return userDao.queryModelByLogin(account,passwordFormat(password));
    }

    @Override
    public String passwordFormat(String password){
        return SecureUtil.md5(password);
    }
}
