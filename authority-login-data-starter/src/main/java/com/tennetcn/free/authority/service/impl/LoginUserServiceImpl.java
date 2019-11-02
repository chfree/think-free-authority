package com.tennetcn.free.authority.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.tennetcn.free.authority.dao.ILoginUserDao;
import com.tennetcn.free.authority.model.LoginUser;
import com.tennetcn.free.authority.service.ILoginUserService;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.message.DaoBaseRuntimeException;
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

    private String passwordFormat(String password){
        return SecureUtil.md5(password);
    }

    @Override
    public LoginUser queryModelByAccount(String account) {
        return userDao.queryModelByAccount(account);
    }

    @Override
    public boolean applyChange(LoginUser user) throws DaoBaseRuntimeException {
        if(ModelStatus.add.equals(user.getModelStatus())){
            if(StringUtils.isEmpty(user.getPassword())){
                user.setPassword(SecureUtil.md5(defaultPwd));
            }
        }
        return super.applyChange(user);
    }
}
