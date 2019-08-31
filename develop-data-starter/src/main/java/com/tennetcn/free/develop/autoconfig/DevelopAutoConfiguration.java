package com.tennetcn.free.develop.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 22:32
 * @comment
 */

@MapperScan(value = "com.tennetcn.free.authority.mapper")
@ImportResource(locations={"develop-starter-config.xml"})
public class DevelopAutoConfiguration {
}
