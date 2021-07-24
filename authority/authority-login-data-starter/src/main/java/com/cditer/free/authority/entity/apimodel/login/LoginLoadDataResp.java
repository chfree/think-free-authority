package com.cditer.free.authority.entity.apimodel.login;

import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.security.message.LoginModel;
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
