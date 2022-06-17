package com.cditer.free.security.autoconfig;

import com.cditer.free.security.intceptor.LoginIntceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:01
 * @comment
 */

@ImportResource(locations={"security-starter-config.xml"})
public class SecurityAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginIntceptor loginIntceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntceptor).addPathPatterns("/api/**").order(-1);
    }
}
