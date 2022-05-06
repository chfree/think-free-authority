package com.cditer.free.security.baseapi;

import com.cditer.free.core.exception.BizException;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.coreweb.message.WebResponseStatus;
import com.cditer.free.coreweb.webapi.FirstApi;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-25 00:20
 * @comment
 */

public class TokenApi extends FirstApi {
    public final static String LOGIN_KEY="loginModel";

    public LoginModel getCurrentLogin() {
      return getCurrentLogin(LoginModel.class);
    }

    public String getLoginId(){
        return getCurrentLogin().getId();
    }

    public String getLoginName(){
        return getCurrentLogin().getName();
    }

    public <T> T getCurrentLogin(Class<? extends T> clazz) {
       return getReqAttr(LOGIN_KEY, clazz);
    }

    public <T> T getReqAttr(String key, Class<? extends T> clazz) {
        T loginModel = (T)servletRequest.getAttribute(key);
        if(loginModel==null){
            throw new BizException(WebResponseStatus.AUTHORIZE_ERROR,"登陆超时，无法获取登陆用户的id");
        }
        return loginModel;
    }
}
