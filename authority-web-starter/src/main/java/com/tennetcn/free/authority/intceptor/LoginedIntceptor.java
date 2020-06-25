package com.tennetcn.free.authority.intceptor;

import com.tennetcn.free.authority.handle.IloginedIntceptor;
import com.tennetcn.free.authority.model.LoginUser;
import com.tennetcn.free.security.message.LoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = "think.authority.logined-callback", havingValue = "base", matchIfMissing = true)
public class LoginedIntceptor implements IloginedIntceptor {
    @Override
    public boolean isAllowLogin(LoginModel loginModel, LoginUser user) {
        log.info("{} : isAllowLogin", this.getClass().getName());
        // 验证用户名和密码后，处理一些业务需要处理的事情
        return true;
    }
}
