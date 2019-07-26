package com.tennetcn.free.authority.apis;

import com.tennetcn.free.web.webapi.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:03
 * @comment
 */

@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
@Api(tags="用户模块",value ="用户相关的操作" )
public class UserApi {

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/user/list")
    public BaseResponse userList(){
        return null;
    }
}
