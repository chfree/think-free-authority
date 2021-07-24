package com.cditer.free.authority.handle;

import com.cditer.free.authority.entity.apimodel.login.RegisterReq;
import com.cditer.free.authority.entity.model.LoginUser;

public interface IRegisterLoginUserIntceptor {
    /**
     * 注册一个登陆用户
     * @param loginUser 被注册的登陆用户实体
     * @param req 注册请求对象
     * @return 是否允许注册
     */
    boolean register(LoginUser loginUser, RegisterReq req);
}
