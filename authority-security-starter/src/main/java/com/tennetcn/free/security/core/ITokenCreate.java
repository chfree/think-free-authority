package com.tennetcn.free.security.core;

import com.tennetcn.free.security.message.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-12-24 09:02
 * @comment
 */

public interface ITokenCreate {
    /**
     * 创建token
     */
    String createToken(LoginModel loginModel);

    /**
     * 创建刷新token
     */
    String createRefreshToken(LoginModel loginModel);
}
