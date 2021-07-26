package com.cditer.free.spring.security.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-07-26 21:16
 * @comment
 */
@Data
@Configuration
public class SecurityConfig {
    @Value("${think.security.open:true}")
    private boolean openSecurity;
}
