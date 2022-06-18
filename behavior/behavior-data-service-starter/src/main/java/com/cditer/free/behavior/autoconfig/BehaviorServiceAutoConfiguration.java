package com.cditer.free.behavior.autoconfig;

import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:01
 * @comment
 */
@MapperScan(value = "com.cditer.free.behavior.mapper")
@ImportResource(locations={"classpath:behavior-data-starter-config.xml"})
public class BehaviorServiceAutoConfiguration {

}
