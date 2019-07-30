package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.button.ButtonListReq;
import com.tennetcn.free.authority.apimodel.button.SaveButtonReq;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:44
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/authority/button/",produces = "application/json;charset=utf-8")
@Api(tags="按钮管理",value ="按钮相关的操作")
public class ButtonApi extends FirstApi {
    @ApiOperation(value = "获取按钮列表")
    @GetMapping("list")
    public BaseResponse list(@Valid ButtonListReq listReq){
        return null;
    }

    @ApiOperation(value = "获取指定按钮")
    @GetMapping("get")
    public BaseResponse get(@Valid String id){
        return null;
    }

    @ApiOperation(value = "删除指定按钮")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个按钮")
    @PostMapping("save")
    public BaseResponse save(SaveButtonReq saveButtonReq){
        return new BaseResponse();
    }
}
