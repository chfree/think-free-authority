package com.tennetcn.free.authority.autoconfig;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:30
 * @comment
 */

@MapperScan(value = "com.tennetcn.free.authority")
@Alias("com.tennetcn.free.authority.model")
@ImportResource(locations={"data-starter-config.xml"})
public class DataAutoConfiguration {

}
