package com.cditer.free.devops.web.apis;


import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.devops.data.entity.apimodel.configpropertie.ConfigPropertieListReq;
import com.cditer.free.devops.data.entity.apimodel.configpropertie.ConfigPropertieListResp;
import com.cditer.free.devops.data.entity.apimodel.configpropertie.SaveConfigPropertieReq;
import com.cditer.free.devops.data.entity.model.ConfigPropertie;
import com.cditer.free.devops.data.entity.viewmodel.ConfigPropertieSearch;
import com.cditer.free.devops.logical.service.IConfigPropertieService;
import com.cditer.free.security.baseapi.TokenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-02 13:58:11
 * @comment     属性配置表
 */
@RestController
@RequestMapping(value = "/api/v1/devops/configPropertie/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="属性配置表管理",value ="属性配置表相关的操作")
public class ConfigPropertieApi extends TokenApi {
    @Autowired
    IConfigPropertieService configPropertieService;

    @ApiOperation(value = "获取属性配置表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid ConfigPropertieListReq listReq){
        ConfigPropertieListResp resp = new ConfigPropertieListResp();
        resp.setTotalCount(configPropertieService.queryCountBySearch(listReq.getSearch()));
        resp.setConfigProperties(configPropertieService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定属性配置表")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "属性配置表id不能为空") String id){
        BaseResponse response=new BaseResponse();

        ConfigPropertie configPropertie = configPropertieService.queryModel(id);
        response.put("configPropertie",configPropertie);

        return response;
    }

    @ApiOperation(value = "搜索属性配置表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(ConfigPropertieSearch search){
        BaseResponse response=new BaseResponse();

        int count =  configPropertieService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个属性配置表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "属性配置表id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  configPropertieService.deleteModel(id);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个属性配置表")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveConfigPropertieReq saveConfigPropertieReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveConfigPropertieReq.getId())){
            saveConfigPropertieReq.setId(PkIdUtils.getId());
            saveConfigPropertieReq.setModelStatus(ModelStatus.add);
        }else{
            saveConfigPropertieReq.setModelStatus(ModelStatus.update);
        }

        boolean result = configPropertieService.applyChange(saveConfigPropertieReq);
        response.put("result",result);
        return response;
    }

}