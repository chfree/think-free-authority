package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.business.BusinessListReq;
import com.tennetcn.free.authority.apimodel.business.BusinessListResp;
import com.tennetcn.free.authority.apimodel.business.SaveBusinessReq;
import com.tennetcn.free.authority.apimodel.user.SaveUserReq;
import com.tennetcn.free.authority.apimodel.user.UserListReq;
import com.tennetcn.free.authority.service.IBusinessService;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:43
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/authority/business/",produces = "application/json;charset=utf-8")
@Api(tags="企业管理",value ="企业相关的操作")
public class BusinessApi extends FirstApi {

    @Autowired
    private IBusinessService businessService;

    @ApiOperation(value = "获取企业列表")
    @GetMapping("list")
    public BusinessListResp list(@Valid BusinessListReq listReq){
        BusinessListResp resp = new BusinessListResp();
        resp.setTotalCount(businessService.queryCount());
        resp.setBusinessList(businessService.queryList(listReq.getPagerModel()));

        return resp;
    }

    @ApiOperation(value = "获取指定企业")
    @GetMapping("get")
    public BaseResponse get(@Valid String id){
        return null;
    }

    @ApiOperation(value = "删除指定企业")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个企业")
    @PostMapping("save")
    public BaseResponse save(SaveBusinessReq businessReq){
        return new BaseResponse();
    }
}
