package com.cditer.free.devops.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-10-01 20:16
 * @comment
 */

@MapperScan(value = "com.cditer.free.devops.logical.mapper")
@ImportResource(locations={"devops-logical-starter-config.xml"})
public class DevopsLogicalAutoConfiguration {
}
