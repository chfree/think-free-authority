package com.cditer.free.login.service.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-09-23 22:28
 * @comment
 */

@MapperScan(value = "com.cditer.free.login.service.logical.mapper")
@ImportResource(locations={"classpath:login-data-boot-config.xml"})
public class LoginDataAutoConfiguration {
}
