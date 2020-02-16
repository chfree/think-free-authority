package com.tennetcn.free.authority.intceptor;

import com.tennetcn.free.authority.configuration.LoginConfig;
import com.tennetcn.free.authority.service.ILoginAuthService;
import com.tennetcn.free.authority.service.ILoginUserService;
import com.tennetcn.free.security.handle.ITokenCheckIntceptor;
import com.tennetcn.free.security.message.LoginModel;
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
        return loginAuthService.checkTokenIsValid(loginModel.getToken());
    }
}
