package com.cditer.free.login.utils;

import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.core.message.security.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-09-23 19:27
 * @comment
 */

public class LoginUtil {

    public static LoginModel user2LoginModel(User loginUser){
        LoginModel loginModel = new LoginModel();
        loginModel.setId(loginUser.getId());
        loginModel.setAccount(loginUser.getAccount());
        loginModel.setName(loginUser.getName());

        return loginModel;
    }
}
