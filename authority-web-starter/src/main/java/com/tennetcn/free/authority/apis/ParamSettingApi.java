package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.data.entity.apimodel.paramsetting.ParamSettingListReq;
import com.tennetcn.free.authority.data.entity.apimodel.paramsetting.ParamSettingListResp;
import com.tennetcn.free.authority.data.entity.apimodel.paramsetting.SaveParamSettingReq;
import com.tennetcn.free.authority.data.entity.model.ParamSetting;
import com.tennetcn.free.authority.data.entity.viewmodel.ParamSettingSearch;
import com.tennetcn.free.authority.service.IParamSettingService;
import com.tennetcn.free.core.util.PkIdUtils;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.core.message.web.BaseResponse;
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
 * @createtime  2020-08-26 12:59:23
 * @comment     参数配置表
 */
@RestController
@RequestMapping(value = "/api/v1/authority/paramSetting/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="参数配置表管理",value ="参数配置表相关的操作")
public class ParamSettingApi extends AuthorityApi {
    @Autowired
    IParamSettingService paramSettingService;

    @ApiOperation(value = "获取参数配置表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid ParamSettingListReq listReq){
        ParamSettingListResp resp = new ParamSettingListResp();
        resp.setTotalCount(paramSettingService.queryCountBySearch(listReq.getSearch()));
        resp.setParamSettings(paramSettingService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定参数配置表")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "参数配置表id不能为空") String id){
        BaseResponse response=new BaseResponse();

        ParamSetting paramSetting = paramSettingService.queryModel(id);
        response.put("paramSetting",paramSetting);

        return response;
    }

    @ApiOperation(value = "搜索参数配置表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(ParamSettingSearch search){
        BaseResponse response=new BaseResponse();

        int count =  paramSettingService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个参数配置表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "参数配置表id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  paramSettingService.deleteModel(id);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个参数配置表")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveParamSettingReq saveParamSettingReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveParamSettingReq.getId())){
            saveParamSettingReq.setId(PkIdUtils.getId());
            saveParamSettingReq.setModelStatus(ModelStatus.add);
        }else{
            saveParamSettingReq.setModelStatus(ModelStatus.update);
        }

        boolean result = paramSettingService.applyChange(saveParamSettingReq);
        response.put("result",result);
        return response;
    }

}