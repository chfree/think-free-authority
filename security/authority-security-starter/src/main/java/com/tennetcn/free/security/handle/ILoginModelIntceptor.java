package com.tennetcn.free.security.handle;

import com.tennetcn.free.security.message.LoginModel;
import io.jsonwebtoken.Claims;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-25 09:39
 * @comment
 */

public interface ILoginModelIntceptor {
    LoginModel registerLoginModel(String token, Claims claims);
}
