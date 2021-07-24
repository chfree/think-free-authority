package com.cditer.free.authority.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-10-04 17:02
 * @comment
 */
@MapperScan(value = "com.cditer.free.authority.logical.mapper")
@ImportResource(locations={"login-data-starter-config.xml"})
public class LoginDataAutoConfiguration {
}
