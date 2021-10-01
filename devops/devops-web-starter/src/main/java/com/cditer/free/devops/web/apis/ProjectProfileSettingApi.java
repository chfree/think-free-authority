package com.cditer.free.devops.web.apis;

import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.devops.data.entity.apimodel.projectprofilesetting.ProjectProfileSettingListReq;
import com.cditer.free.devops.data.entity.apimodel.projectprofilesetting.ProjectProfileSettingListResp;
import com.cditer.free.devops.data.entity.apimodel.projectprofilesetting.SaveProjectProfileSettingReq;
import com.cditer.free.devops.data.entity.model.ProjectProfileSetting;
import com.cditer.free.devops.data.entity.viewmodel.ProjectProfileSettingSearch;
import com.cditer.free.devops.logical.service.IProjectProfileSettingService;
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
 * @createtime  2021-10-01 20:53:56
 * @comment     项目环境配置
 */
@RestController
@RequestMapping(value = "/api/v1/devops/projectProfileSetting/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="项目环境配置管理",value ="项目环境配置相关的操作")
public class ProjectProfileSettingApi extends TokenApi {
    @Autowired
    IProjectProfileSettingService projectProfileSettingService;

    @ApiOperation(value = "获取项目环境配置列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid ProjectProfileSettingListReq listReq){
        ProjectProfileSettingListResp resp = new ProjectProfileSettingListResp();
        resp.setTotalCount(projectProfileSettingService.queryCountBySearch(listReq.getSearch()));
        resp.setProjectProfileSettings(projectProfileSettingService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定项目环境配置")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "项目环境配置id不能为空") String id){
        BaseResponse response=new BaseResponse();

        ProjectProfileSetting projectProfileSetting = projectProfileSettingService.queryModel(id);
        response.put("projectProfileSetting",projectProfileSetting);

        return response;
    }

    @ApiOperation(value = "搜索项目环境配置数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(ProjectProfileSettingSearch search){
        BaseResponse response=new BaseResponse();

        int count =  projectProfileSettingService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个项目环境配置")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "项目环境配置id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  projectProfileSettingService.deleteModel(id);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个项目环境配置")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveProjectProfileSettingReq saveProjectProfileSettingReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveProjectProfileSettingReq.getId())){
            saveProjectProfileSettingReq.setId(PkIdUtils.getId());
            saveProjectProfileSettingReq.setModelStatus(ModelStatus.add);
        }else{
            saveProjectProfileSettingReq.setModelStatus(ModelStatus.update);
        }

        boolean result = projectProfileSettingService.applyChange(saveProjectProfileSettingReq);
        response.put("result",result);
        return response;
    }

}