package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.model.User;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

public interface IUserService extends ISuperService<User> {
    List<User> queryListMPByIds(List<String> ids);

    String getLoginUserNamesByIds(List<String> ids);

    Boolean queryBylogin(String username,String password);
}
