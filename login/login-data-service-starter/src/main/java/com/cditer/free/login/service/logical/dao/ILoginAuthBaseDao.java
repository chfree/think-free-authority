package com.cditer.free.login.service.logical.dao;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;
import com.cditer.free.login.service.logical.entity.model.LoginAuthBase;
import com.cditer.free.login.service.logical.entity.viewmodel.LoginAuthSearch;
import com.cditer.free.login.service.logical.entity.viewmodel.LoginAuthBaseView;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:32:07
 * @comment     登陆授权表
 */

public interface ILoginAuthBaseDao extends ISuperDao<LoginAuthBase>{
    int queryCountBySearch(LoginAuthSearch search);

    List<LoginAuthBaseView> queryListBySearch(LoginAuthSearch search, PagerModel pagerModel);

    boolean updateStatusByUserId(String userId,String status);

    boolean checkTokenIsValid(String token);

    boolean updateStatusByToken(String token,String status);
}