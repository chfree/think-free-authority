package com.cditer.free.spring.security.autoconfig;

import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:01
 * @comment
 */

@ImportResource(locations={"spring-security-starter-config.xml"})
public class SpringSecurityAutoConfiguration implements WebMvcConfigurer {
}
