package com.tennetcn.free.authority.handle;

import com.tennetcn.free.authority.model.LoginUser;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.message.LoginModel;

/**
 * 登陆成功后的回掉
 * 用在验证完用户名和密码后的回调
 */
public interface ILoginedIntceptor {
    int getOrder();

    void logined(BaseResponse response,LoginModel loginModel, LoginUser loginUser);
}
