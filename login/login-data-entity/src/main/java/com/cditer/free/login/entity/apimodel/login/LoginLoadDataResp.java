package com.cditer.free.login.entity.apimodel.login;

import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.message.web.BaseResponse;
import lombok.Data;

@Data
public class LoginLoadDataResp extends BaseResponse {
    private LoginModel loginInfo;
}
