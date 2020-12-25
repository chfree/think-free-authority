package com.tennetcn.free.authority.utils;

import com.tennetcn.free.authority.entity.model.LoginUser;
import com.tennetcn.free.security.message.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-25 14:05
 * @comment
 */

public class LoginUtil {
    public static LoginModel user2LoginModel(LoginUser user){
        LoginModel loginModel = new LoginModel();
        loginModel.setId(user.getId());
        loginModel.setAccount(user.getAccount());
        loginModel.setName(user.getName());

        loginModel.put("user", user);

        return loginModel;
    }
}
