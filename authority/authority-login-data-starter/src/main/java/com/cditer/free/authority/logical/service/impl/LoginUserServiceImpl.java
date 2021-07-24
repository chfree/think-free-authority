package com.cditer.free.authority.logical.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.cditer.free.authority.entity.model.LoginUser;
import com.cditer.free.authority.entity.viewmodel.LoginUserSearch;
import com.cditer.free.authority.logical.dao.ILoginUserDao;
import com.cditer.free.authority.logical.service.ILoginUserService;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.data.dao.base.impl.SuperService;
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
public class LoginUserServiceImpl extends SuperService<LoginUser> implements ILoginUserService {

    private final String defaultPwd = "000000";

    @Autowired
    ILoginUserDao userDao;

    @Override
    public LoginUser queryModelByLogin(String account, String password) {
        return userDao.queryModelByLogin(account,passwordFormat(password));
    }

    @Override
    public String passwordFormat(String password){
        return SecureUtil.md5(password);
    }

    @Override
    public LoginUser queryModelByAccount(String account) {
        return userDao.queryModelByAccount(account);
    }

    @Override
    public int queryCountByLoginUserSearch(LoginUserSearch search) {
        return userDao.queryCountByLoginUserSearch(search);
    }

    @Override
    public boolean applyChange(LoginUser user) throws DaoBaseRuntimeException {
        if(ModelStatus.add.equals(user.getModelStatus())){
            if(StringUtils.isEmpty(user.getPassword())){
                user.setPassword(passwordFormat(defaultPwd));
            }
        }
        return super.applyChange(user);
    }
}
