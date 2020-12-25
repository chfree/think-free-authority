package com.tennetcn.free.authority.logical.dao;

import com.tennetcn.free.authority.entity.viewmodel.LoginAuthView;
import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.authority.entity.viewmodel.LoginAuthSearch;
import com.tennetcn.free.authority.entity.model.LoginAuth;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:32:07
 * @comment     登陆授权表
 */

public interface ILoginAuthDao extends ISuperDao<LoginAuth>{
    int queryCountBySearch(LoginAuthSearch search);

    List<LoginAuthView> queryListBySearch(LoginAuthSearch search, PagerModel pagerModel);

    boolean updateStatusByUserId(String userId,String status);

    boolean checkTokenIsValid(String token);

    boolean updateStatusByToken(String token,String status);
}