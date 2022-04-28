package com.cditer.free.security.intceptor;

import com.cditer.free.core.inceptor.ILoginModelQuery;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.security.baseapi.TokenApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Component
@Qualifier("loginModelQueryTokenImpl")
public class LoginModelQueryTokenImpl implements ILoginModelQuery {
    @Autowired
    protected HttpServletRequest servletRequest;

    @Override
    public LoginModel getCurrentLogin() {
        if(servletRequest==null){
            log.warn("在非request线程中，无法获取登录信息");
            return null;
        }
        LoginModel loginModel = (LoginModel)servletRequest.getAttribute(TokenApi.LOGIN_KEY);
        if(loginModel==null){
            log.warn(String.format("在request线程中，无法获取key为%的登录信息", TokenApi.LOGIN_KEY));
        }
        return loginModel;
    }
}
