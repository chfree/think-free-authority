package com.cditer.free.security.handle;

import com.cditer.free.core.message.security.LoginModel;

public interface ITokenCheckInceptor {
    int getOrder();

    void checkToken(LoginModel loginModel,String token);
}
