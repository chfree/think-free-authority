package com.cditer.free.login.service.logical.service.impl;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.login.service.configuration.LoginConfig;
import com.cditer.free.login.service.logical.dao.ILoginAuthBaseDao;
import com.cditer.free.login.entity.model.LoginAuthBase;
import com.cditer.free.login.entity.viewmodel.LoginAuthSearch;
import com.cditer.free.login.entity.viewmodel.LoginAuthBaseView;
import com.cditer.free.login.enums.LoginAuthStatus;
import com.cditer.free.login.service.logical.service.ILoginAuthBaseService;
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
public class LoginAuthBaseServiceImpl extends SuperService<LoginAuthBase> implements ILoginAuthBaseService {
    @Autowired
    ILoginAuthBaseDao loginAuthDao;

    @Autowired
    LoginConfig loginConfig;

    @Override
    public int queryCountBySearch(LoginAuthSearch search) {
        return loginAuthDao.queryCountBySearch(search);
    }

    @Override
    public List<LoginAuthBaseView> queryListBySearch(LoginAuthSearch search, PagerModel pagerModel) {
        return loginAuthDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public boolean saveLoginAuth(LoginAuthBase loginAuthBase) {
        if(loginConfig.isOpenSSO()){
            // 先将当前用户的其他的token置为无效
            if(!loginAuthDao.updateStatusByUserId(loginAuthBase.getUserId(), LoginAuthStatus.INVALID.getValue())){
                return false;
            }
        }
        return addModel(loginAuthBase);
    }

    @Override
    public boolean checkTokenIsValid(String token) {
        return loginAuthDao.checkTokenIsValid(token);
    }

    @Override
    public boolean updateStatusByToken(String token, String status) {
        return loginAuthDao.updateStatusByToken(token,status);
    }

}