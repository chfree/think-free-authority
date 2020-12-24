package com.tennetcn.free.security.core;

import com.tennetcn.free.security.message.LoginModel;
import org.springframework.stereotype.Component;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-12-24 09:09
 * @comment
 */

@Component(value = CreateTokenFactory.DEFAULT_TOKEN_CREATE)
public class DefaultTokenCreate implements ITokenCreate{
    @Override
    public String createToken(LoginModel loginModel) {
        return null;
    }

    @Override
    public String createRefreshToken(LoginModel loginModel) {
        return null;
    }
}
