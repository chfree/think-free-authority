package com.tennetcn.free.authority.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-02-16 10:35
 * @comment
 */

@Data
@Configurable
@Component
public class LoginConfig {
    /**
     * 开启单点登录
     */
    @Value("${think.login.openSSO:true}")
    private boolean openSSO;

    /**
     * 登陆是否加密
     */
    @Value("${think.login.encrypt:true}")
    private boolean encrypt;
}
