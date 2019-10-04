package com.tennetcn.free.authority.handle;

import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.web.webapi.BaseResponse;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-10-04 11:54
 * @comment
 */

public interface ILoginedInceptor {
    void additionLoginModel(LoginModel loginModel, BaseResponse response);
}
