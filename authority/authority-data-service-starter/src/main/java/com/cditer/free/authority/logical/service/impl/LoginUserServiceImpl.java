package com.cditer.free.authority.logical.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.authority.data.entity.viewmodel.LoginUserSearch;
import com.cditer.free.authority.data.entity.viewmodel.UserSearch;
import com.cditer.free.authority.logical.dao.IUserDao;
import com.cditer.free.authority.logical.service.ILoginUserService;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.data.message.DaoBaseRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

@Component
public class LoginUserServiceImpl implements ILoginUserService {

    private final String defaultPwd = "000000";

    @Autowired
    IUserDao userDao;

    @Override
    public User queryModelByLogin(String account, String password) {
        return userDao.queryModelByLogin(account,passwordFormat(password));
    }

    @Override
    public String passwordFormat(String password){
        return SecureUtil.md5(password);
    }

    @Override
    public User queryModelByAccount(String account) {
        return userDao.queryModelByAccount(account);
    }

    @Override
    public int queryCountByLoginUserSearch(LoginUserSearch search) {
        return userDao.queryCountByLoginUserSearch(search);
    }

    @Override
    public boolean applyChange(User user) throws DaoBaseRuntimeException {
        if(ModelStatus.add.equals(user.getModelStatus())){
            if(StringUtils.isEmpty(user.getPassword())){
                user.setPassword(passwordFormat(defaultPwd));
            }
        }
        return userDao.applyChange(user);
    }

    @Override
    public User queryCountByUserId(String userId) {
        UserSearch userSearch = new UserSearch();
        userSearch.setId(userId);

        return userDao.queryModelBySearch(userSearch);
    }
}
