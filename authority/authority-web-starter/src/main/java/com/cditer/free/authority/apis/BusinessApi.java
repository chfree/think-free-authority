package com.cditer.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.cditer.free.authority.data.entity.apimodel.business.BusinessListReq;
import com.cditer.free.authority.data.entity.apimodel.business.BusinessListResp;
import com.cditer.free.authority.data.entity.apimodel.business.SaveBusinessReq;
import com.cditer.free.authority.data.entity.model.Business;
import com.cditer.free.authority.logical.service.IBusinessService;
import com.cditer.free.authority.data.entity.viewmodel.BusinessSearch;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.security.baseapi.TokenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/api/v1/authority/business/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="企业管理",value ="企业相关的操作")
public class BusinessApi extends TokenApi {

    @Autowired
    private IBusinessService businessService;

    @ApiOperation(value = "获取企业列表")
    @PostMapping("list")
    public BusinessListResp list(@RequestBody  @Valid BusinessListReq listReq){
        BusinessListResp resp = new BusinessListResp();
        resp.setTotalCount(businessService.queryCountBySearch(listReq.getSearch()));
        resp.setBusinessList(businessService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        cached.put("test","chenghuan");

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
