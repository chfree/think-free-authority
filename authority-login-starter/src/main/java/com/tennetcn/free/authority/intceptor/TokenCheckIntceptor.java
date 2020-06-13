package com.tennetcn.free.authority.intceptor;

import com.tennetcn.free.authority.configuration.LoginConfig;
import com.tennetcn.free.authority.service.ILoginAuthService;
import com.tennetcn.free.authority.service.ILoginUserService;
import com.tennetcn.free.core.exception.BizException;
import com.tennetcn.free.core.message.web.ResponseStatus;
import com.tennetcn.free.security.handle.ITokenCheckIntceptor;
import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.web.message.WebResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-16 11:12
 * @comment
 */

@Component
public class TokenCheckIntceptor implements ITokenCheckIntceptor {

    @Autowired
    LoginConfig loginConfig;

    @Autowired
    ILoginAuthService loginAuthService;

    @Override
    public boolean checkToken(LoginModel loginModel) {
        if(!loginConfig.isOpenSSO()){
            return true;
        }
        boolean result = loginAuthService.checkTokenIsValid(loginModel.getToken());
        if(!result){
            throw new BizException(WebResponseStatus.AUTHORIZE_ERROR,"您已经在其他位置登陆，请确认账号密码是否被盗");
        }
        return result;
    }
}
