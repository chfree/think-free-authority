package com.cditer.free.authority.intceptor;

import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.authority.handle.ILoginAllowIntceptor;
import com.cditer.free.security.message.LoginModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
//@ConditionalOnProperty(name = "think.authority.logined-callback", havingValue = "base", matchIfMissing = true)
public class LoginAllowIntceptor implements ILoginAllowIntceptor {
    @Override
    public boolean isAllowLogin(LoginModel loginModel, User user) {
        log.info("{} : isAllowLogin", this.getClass().getName());
        // 验证用户名和密码后，处理一些业务需要处理的事情
        return true;
    }
}
