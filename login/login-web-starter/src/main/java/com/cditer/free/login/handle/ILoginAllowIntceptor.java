package com.cditer.free.login.handle;

import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.core.message.security.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-11-02 12:39
 * @comment
 */

public interface ILoginAllowIntceptor {
    boolean isAllowLogin(LoginModel loginModel, User user);
}
