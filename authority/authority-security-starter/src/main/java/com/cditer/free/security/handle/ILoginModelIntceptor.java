package com.cditer.free.security.handle;

import com.cditer.free.security.message.LoginModel;
import io.jsonwebtoken.Claims;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-25 09:39
 * @comment 注册loginModel的回调
 */

public interface ILoginModelIntceptor {
    LoginModel registerLoginModel(String token, Claims claims);
}
