package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.business.BusinessListReq;
import com.tennetcn.free.authority.apimodel.business.BusinessListResp;
import com.tennetcn.free.authority.apimodel.business.SaveBusinessReq;
import com.tennetcn.free.authority.model.Business;
import com.tennetcn.free.authority.service.IBusinessService;
import com.tennetcn.free.authority.viewmodel.BusinessSearch;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("list")
    public BusinessListResp list(@RequestBody  @Valid BusinessListReq listReq){
        BusinessListResp resp = new BusinessListResp();
        resp.setTotalCount(businessService.queryCountBySearch(listReq.getSearch()));
        resp.setBusinessList(businessService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定企业")
    @GetMapping("get")
    public BaseResponse get(@Valid String id){
        BaseResponse response=new BaseResponse();

        Business business = businessService.queryModel(id);
        response.put("business",business);

        return response;
    }

    @ApiOperation(value = "搜索企业数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(BusinessSearch search){
        BaseResponse response=new BaseResponse();

        int count =  businessService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定企业")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个企业")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveBusinessReq businessReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(businessReq.getId())){
            businessReq.setId(IdUtil.randomUUID());
            businessReq.setModelStatus(ModelStatus.add);
        }else{
            businessReq.setModelStatus(ModelStatus.update);
        }

        boolean result = businessService.applyChange(businessReq);
        response.put("result",result);

        return response;
    }
}
