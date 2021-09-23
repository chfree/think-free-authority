package com.cditer.free.authority.apis;


import cn.hutool.core.util.IdUtil;
import com.cditer.free.authority.data.entity.apimodel.auth.LoginAuthListReq;
import com.cditer.free.authority.data.entity.apimodel.auth.LoginAuthListResp;
import com.cditer.free.authority.data.entity.apimodel.auth.SaveLoginAuthReq;
import com.cditer.free.authority.data.entity.model.LoginAuth;
import com.cditer.free.authority.data.entity.viewmodel.LoginAuthSearch;
import com.cditer.free.authority.logical.service.ILoginAuthService;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.usersever.logical.enums.LoginAuthStatus;
import com.cditer.free.web.security.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:43:33
 * @comment     登陆授权表
 */
@RestController
@RequestMapping(value = "/api/v1/authority/loginAuth/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="登陆授权表管理",value ="登陆授权表相关的操作")
public class LoginAuthApi extends AuthorityApi {
    @Autowired
    ILoginAuthService loginAuthService;

    @ApiOperation(value = "获取登陆授权表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid LoginAuthListReq listReq){
        LoginAuthListResp resp = new LoginAuthListResp();
        resp.setTotalCount(loginAuthService.queryCountBySearch(listReq.getSearch()));
        resp.setLoginAuths(loginAuthService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定登陆授权表")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "登陆授权表id不能为空") String id){
        BaseResponse response=new BaseResponse();

        LoginAuth loginAuth = loginAuthService.queryModel(id);
        response.put("loginAuth",loginAuth);

        return response;
    }

    @ApiOperation(value = "搜索登陆授权表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(LoginAuthSearch search){
        BaseResponse response=new BaseResponse();

        int count =  loginAuthService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "设置为无效")
    @PostMapping("setInvalid")
    public BaseResponse setInvalid(@Valid @NotBlank(message = "授权id不能为空") String id){
        BaseResponse response=new BaseResponse();

        LoginAuth loginAuth = loginAuthService.queryModel(id);
        loginAuth.setStatus(LoginAuthStatus.INVALID.getValue());

        response.put("result",loginAuthService.updateModel(loginAuth));

        return response;
    }

    @ApiOperation(value = "删除一个登陆授权表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "登陆授权表id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  loginAuthService.deleteModel(id);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个登陆授权表")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveLoginAuthReq saveLoginAuthReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveLoginAuthReq.getId())){
            saveLoginAuthReq.setId(IdUtil.randomUUID());
            saveLoginAuthReq.setModelStatus(ModelStatus.add);
        }else{
            saveLoginAuthReq.setModelStatus(ModelStatus.update);
        }

        boolean result = loginAuthService.applyChange(saveLoginAuthReq);
        response.put("result",result);
        return response;
    }

}