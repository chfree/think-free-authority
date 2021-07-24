package com.cditer.free.security.core;

import com.cditer.free.security.message.LoginModel;

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
    String createToken(String userId,String account,String name);

    /**
     * 创建刷新token
     */
    String createRefreshToken(String userId,String account,String name);
}
