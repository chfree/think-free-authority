package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.login.LoginReq;
import com.tennetcn.free.authority.service.IUserService;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:05
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/authority/login/",produces = "application/json;charset=utf-8")
@Api(tags="登陆模块",value ="登陆相关的操作" )
public class LoginApi extends FirstApi {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "登陆")
    @GetMapping("login")
    public BaseResponse login(@Valid LoginReq loginReq){
        BaseResponse response = new BaseResponse();
        response.put("token", IdUtil.simpleUUID());

        return response;
    }

    @GetMapping("logout")
    public BaseResponse logout(){
        return new BaseResponse();
    }
}
