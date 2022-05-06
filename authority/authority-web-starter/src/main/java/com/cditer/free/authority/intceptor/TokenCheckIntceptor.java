package com.cditer.free.authority.intceptor;

import com.cditer.free.authority.configuration.LoginConfig;
import com.cditer.free.authority.logical.service.ILoginAuthService;
import com.cditer.free.core.exception.BizException;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.coreweb.message.WebResponseStatus;
import com.cditer.free.security.handle.ITokenCheckInceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenCheckIntceptor implements ITokenCheckInceptor {

    @Autowired
    LoginConfig loginConfig;

    @Autowired
    ILoginAuthService loginAuthService;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void checkToken(LoginModel loginModel, String token) {
        if(!loginConfig.isOpenSSO()){
            return;
        }
        boolean result = loginAuthService.checkTokenIsValid(token);
        if(!result){
            throw new BizException(WebResponseStatus.AUTHORIZE_ERROR, "您已经在其他位置登录，当前会话无效");
        }
    }
}
