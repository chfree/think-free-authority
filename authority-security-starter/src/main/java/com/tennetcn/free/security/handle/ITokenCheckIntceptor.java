package com.tennetcn.free.security.handle;

import com.tennetcn.free.security.message.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-16 10:59
 * @comment
 */

public interface ITokenCheckIntceptor {
    boolean checkToken(LoginModel loginModel);
}
