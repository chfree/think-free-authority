package com.tennetcn.free.security.webapi;

import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.web.webapi.FirstApi;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-25 00:20
 * @comment
 */

public class AuthorityApi extends FirstApi {
    public final static String LOGIN_KEY="loginModel";

    public LoginModel getCurrentLogin() {
        return (LoginModel)servletRequest.getAttribute(LOGIN_KEY);
    }
}
