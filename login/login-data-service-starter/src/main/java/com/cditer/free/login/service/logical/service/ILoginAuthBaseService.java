package com.cditer.free.login.service.logical.service;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.login.service.logical.entity.model.LoginAuthBase;
import com.cditer.free.login.service.logical.entity.viewmodel.LoginAuthSearch;
import com.cditer.free.login.service.logical.entity.viewmodel.LoginAuthBaseView;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:33:16
 * @comment     登陆授权表
 */

public interface ILoginAuthBaseService extends ISuperService<LoginAuthBase>{
    int queryCountBySearch(LoginAuthSearch search);

    List<LoginAuthBaseView> queryListBySearch(LoginAuthSearch search, PagerModel pagerModel);

    boolean saveLoginAuth(LoginAuthBase loginAuthBase);

    boolean checkTokenIsValid(String token);

    boolean updateStatusByToken(String token,String status);
}