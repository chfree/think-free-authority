package com.cditer.free.param.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(value = "com.cditer.free.param.logical.mapper")
@ImportResource(locations={"classpath*:param-logical-starter-config.xml"})
public class ParamLogicalAutoConfiguration {

}
