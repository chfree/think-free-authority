package com.cditer.free.authority.data.entity.apimodel.login;

import com.cditer.free.core.message.web.BaseRequest;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterReq extends BaseRequest {
    @NotBlank(message = "账号不能为空")
    @Email(message = "邮箱格式不正确")
    private String account;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}