package com.cditer.free.devops.web.apis;


import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.devops.data.entity.apimodel.projectinfo.ProjectInfoListReq;
import com.cditer.free.devops.data.entity.apimodel.projectinfo.ProjectInfoListResp;
import com.cditer.free.devops.data.entity.apimodel.projectinfo.SaveProjectInfoReq;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;
import com.cditer.free.devops.logical.service.IProjectInfoService;
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
 * @createtime  2021-10-01 20:38:01
 * @comment     项目信息表
 */
@RestController
@RequestMapping(value = "/api/v1/devops/projectInfo/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="项目信息表管理",value ="项目信息表相关的操作")
public class ProjectInfoApi extends TokenApi {
    @Autowired
    IProjectInfoService projectInfoService;

    @ApiOperation(value = "获取项目信息表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid ProjectInfoListReq listReq){
        ProjectInfoListResp resp = new ProjectInfoListResp();
        resp.setTotalCount(projectInfoService.queryCountBySearch(listReq.getSearch()));
        resp.setProjectInfos(projectInfoService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定项目信息表")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "项目信息表id不能为空") String id){
        BaseResponse response=new BaseResponse();

        ProjectInfo projectInfo = projectInfoService.queryModel(id);
        response.put("projectInfo",projectInfo);

        return response;
    }

    @ApiOperation(value = "搜索项目信息表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(ProjectInfoSearch search){
        BaseResponse response=new BaseResponse();

        int count =  projectInfoService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个项目信息表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "项目信息表id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  projectInfoService.deleteModel(id);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个项目信息表")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveProjectInfoReq saveProjectInfoReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveProjectInfoReq.getId())){
            saveProjectInfoReq.setId(PkIdUtils.getId());
            saveProjectInfoReq.setModelStatus(ModelStatus.add);
        }else{
            saveProjectInfoReq.setModelStatus(ModelStatus.update);
        }

        boolean result = projectInfoService.applyChange(saveProjectInfoReq);
        response.put("result",result);
        return response;
    }

}