package com.cditer.free.security.handle;

import com.cditer.free.core.message.security.LoginModel;

import javax.servlet.http.HttpServletRequest;

public interface ITokenLoginModelInceptor {
    int getOrder();

    void requestSetLoginModel(HttpServletRequest request, LoginModel loginModel);
}
