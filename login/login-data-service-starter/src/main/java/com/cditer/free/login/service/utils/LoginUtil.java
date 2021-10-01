package com.cditer.free.login.service.utils;

import com.cditer.free.login.entity.model.LoginUser;
import com.cditer.free.core.message.security.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-09-23 19:27
 * @comment
 */

public class LoginUtil {

    public static LoginModel user2LoginModel(LoginUser loginUser){
        LoginModel loginModel = new LoginModel();
        loginModel.setId(loginUser.getId());
        loginModel.setAccount(loginUser.getAccount());
        loginModel.setName(loginUser.getName());

        return loginModel;
    }
}
