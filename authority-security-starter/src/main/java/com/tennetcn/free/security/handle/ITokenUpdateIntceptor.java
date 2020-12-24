package com.tennetcn.free.security.handle;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-12-24 12:59
 * @comment
 */

public interface ITokenUpdateIntceptor {
    void tokenUpdate(String userId,String token,String refreshToken);
}
