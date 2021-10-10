package com.cditer.free.login.entity.apimodel.login;

import com.cditer.free.core.message.web.BaseResponse;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-09-20 19:33
 * @comment
 */

@Data
public class CheckUserLoginResp extends BaseResponse {
    /**
     * 是否允许登陆
     */
    private boolean allowLogin;
}
