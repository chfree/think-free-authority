package com.tennetcn.free.authority.viewmodel;

import com.tennetcn.free.authority.model.LoginAuth;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-15 22:36
 * @comment
 */

@Data
public class LoginAuthView extends LoginAuth {
    private String account;

    private String name;
}
