package com.tennetcn.free.authority.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-10-04 17:02
 * @comment
 */
@MapperScan(value = "com.tennetcn.free.authority.mapper")
@ImportResource(locations={"login-data-starter-config.xml"})
public class LoginDataAutoConfiguration {
}
