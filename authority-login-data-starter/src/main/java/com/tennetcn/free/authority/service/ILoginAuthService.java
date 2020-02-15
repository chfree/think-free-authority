package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.model.LoginAuth;
import com.tennetcn.free.authority.viewmodel.LoginAuthSearch;
import com.tennetcn.free.authority.viewmodel.LoginAuthView;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:33:16
 * @comment     登陆授权表
 */

public interface ILoginAuthService extends ISuperService<LoginAuth>{
    int queryCountBySearch(LoginAuthSearch search);

    List<LoginAuthView> queryListBySearch(LoginAuthSearch search, PagerModel pagerModel);
}