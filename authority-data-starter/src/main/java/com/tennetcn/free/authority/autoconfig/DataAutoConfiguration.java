package com.tennetcn.free.authority.autoconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.Alias;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.annotation.TypeAlias;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:30
 * @comment
 */

@MapperScan(value = "com.tennetcn.free.authority.mapper")
@ImportResource(locations={"data-starter-config.xml"})
public class DataAutoConfiguration {
}
