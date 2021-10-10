package com.cditer.free.login.handle;

import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.login.entity.apimodel.login.RegisterReq;

public interface IRegisterLoginUserIntceptor {
    /**
     * 注册一个登陆用户
     * @param user 被注册的登陆用户实体
     * @param req 注册请求对象
     * @return 是否允许注册
     */
    boolean register(User user, RegisterReq req);
}
