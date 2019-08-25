package com.tennetcn.free.authority.intceptor;

import com.tennetcn.free.authority.model.User;
import com.tennetcn.free.authority.service.IUserService;
import com.tennetcn.free.authority.utils.LoginUtil;
import com.tennetcn.free.security.handle.ILoginModelIntceptor;
import com.tennetcn.free.security.message.LoginModel;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-25 14:00
 * @comment
 */

@Component
public class LoginModelIntceptor implements ILoginModelIntceptor {

    @Autowired
    IUserService userService;

    @Override
    public LoginModel registerLoginModel(String token, Claims claims) {
        String account = claims.get("account",String.class);

        User user = userService.queryModelByAccount(account);
        if(user==null){
            return null;
        }

        return LoginUtil.user2LoginModel(user);
    }
}
