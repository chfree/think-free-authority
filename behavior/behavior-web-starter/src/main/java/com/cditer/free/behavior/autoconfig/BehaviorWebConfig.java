package com.cditer.free.behavior.autoconfig;

import com.cditer.free.behavior.inceptor.WebVisitLogInceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Configuration
public class BehaviorWebConfig implements WebMvcConfigurer {

    @Autowired
    private WebVisitLogInceptor webVisitLogInceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webVisitLogInceptor).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
