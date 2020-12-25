package com.tennetcn.free.authority.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:30
 * @comment
 */

@MapperScan(value = "com.tennetcn.free.authority.logical.mapper")
@ImportResource(locations={"data-starter-config.xml"})
public class DataAutoConfiguration {
}
