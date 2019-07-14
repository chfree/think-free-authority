package com.tennetcn.free.authority.apis;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.tennetcn.free.authority.model.User;
import com.tennetcn.free.authority.service.IUserService;
import com.tennetcn.free.data.message.DaoBaseException;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:05
 * @comment
 */

@RestController
public class LoginApi extends FirstApi {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public BaseResponse login(String username, String password){
        BaseResponse response = new BaseResponse();
        response.put("result",userService.queryBylogin(username,password));
        response.put("token", IdUtil.simpleUUID());

        return response;
    }
}
