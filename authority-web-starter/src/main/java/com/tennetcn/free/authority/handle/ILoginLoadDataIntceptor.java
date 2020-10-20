package com.tennetcn.free.authority.handle;

import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.message.LoginModel;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-10-20 12:31
 * @comment 登陆后走loading的时候需要加载数据的回调
 */

public interface ILoginLoadDataIntceptor {
    /**
     * 在登陆后的loading界面，附加更多的请求信息
     */
    void additionLoginModel(LoginModel loginModel, BaseResponse response);
}