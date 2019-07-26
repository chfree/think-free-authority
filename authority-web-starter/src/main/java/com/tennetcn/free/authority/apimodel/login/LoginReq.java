package com.tennetcn.free.authority.apimodel.login;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-25 15:57
 * @comment
 */

@Data
public class LoginReq {
    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message="密码不能为空")
    private String password;
}
