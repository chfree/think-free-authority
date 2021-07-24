package com.cditer.free.authority.data.entity.apimodel.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-09 13:53
 * @comment
 */

@Data
public class UpdatePwd {
    @NotBlank(message = "用户账号不能为空")
    private String account;

    @NotBlank(message = "旧密码不能为空")
    private String oldPwd;

    @NotBlank(message = "新密码不能为空")
    private String newPwd;

    @NotBlank(message = "确认密码不能为空")
    private String confirmNewPwd;
}
