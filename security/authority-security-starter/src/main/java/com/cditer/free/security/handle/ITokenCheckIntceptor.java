package com.cditer.free.security.handle;

import com.cditer.free.security.message.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-16 10:59
 * @comment
 */

public interface ITokenCheckIntceptor {
    /**
     * 处理完通用的token检查后，在进行自定义的token检查处理
     */
    boolean checkToken(LoginModel loginModel);
}
