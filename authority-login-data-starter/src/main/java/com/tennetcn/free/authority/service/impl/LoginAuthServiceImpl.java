package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.configuration.LoginConfig;
import com.tennetcn.free.authority.dao.ILoginAuthDao;
import com.tennetcn.free.authority.enums.LoginAuthStatus;
import com.tennetcn.free.authority.model.LoginAuth;
import com.tennetcn.free.authority.service.ILoginAuthService;
import com.tennetcn.free.authority.viewmodel.LoginAuthSearch;
import com.tennetcn.free.authority.viewmodel.LoginAuthView;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:33:37
 * @comment     登陆授权表
 */

@Component
public class LoginAuthServiceImpl extends SuperService<LoginAuth> implements ILoginAuthService{
    @Autowired
    ILoginAuthDao loginAuthDao;

    @Autowired
    LoginConfig loginConfig;

    @Override
    public int queryCountBySearch(LoginAuthSearch search) {
        return loginAuthDao.queryCountBySearch(search);
    }

    @Override
    public List<LoginAuthView> queryListBySearch(LoginAuthSearch search, PagerModel pagerModel) {
        return loginAuthDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public boolean saveLoginAuth(LoginAuth loginAuth) {
        if(loginConfig.isOpenSSO()){
            // 先将当前用户的其他的token置为无效
            if(!loginAuthDao.updateStatusByUserId(loginAuth.getUserId(), LoginAuthStatus.INVALID.getKey())){
                return false;
            }
        }
        return addModel(loginAuth);
    }

    @Override
    public boolean checkTokenIsValid(String token) {
        return loginAuthDao.checkTokenIsValid(token);
    }

}