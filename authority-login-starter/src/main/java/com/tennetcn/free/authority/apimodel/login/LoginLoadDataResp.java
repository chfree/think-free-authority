package com.tennetcn.free.authority.apimodel.login;

import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.web.webapi.BaseResponse;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-26 16:18
 * @comment
 */

@Data
public class LoginLoadDataResp extends BaseResponse {
    private LoginModel loginInfo;
}
