package com.tennetcn.free.file.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:30
 * @comment
 */

@MapperScan(value = "com.tennetcn.free.file.mapper")
@ImportResource(locations={"file-data-starter-config.xml"})
public class FIleDataServiceAutoConfiguration {
}
