package com.cditer.free.login.service.logical.entity.viewmodel;

import com.cditer.free.login.service.logical.entity.model.LoginAuthBase;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-15 22:36
 * @comment
 */

@Data
public class LoginAuthBaseView extends LoginAuthBase {
    private String account;

    private String name;
}
