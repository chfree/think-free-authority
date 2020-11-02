package com.tennetcn.free.authority.handle;

import com.tennetcn.free.authority.model.LoginUser;
import com.tennetcn.free.security.message.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-11-02 12:39
 * @comment
 */

public interface ILoginAllowIntceptor {
    boolean isAllowLogin(LoginModel loginModel, LoginUser user);
}
