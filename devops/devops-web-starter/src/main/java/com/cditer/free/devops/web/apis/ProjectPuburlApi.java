package com.cditer.free.devops.web.apis;

import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.devops.data.entity.apimodel.projectpuburl.ProjectPuburlListReq;
import com.cditer.free.devops.data.entity.apimodel.projectpuburl.ProjectPuburlListResp;
import com.cditer.free.devops.data.entity.apimodel.projectpuburl.SaveProjectPuburlReq;
import com.cditer.free.devops.data.entity.model.ProjectPuburl;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlSearch;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlView;
import com.cditer.free.devops.logical.service.IProjectPuburlService;
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
 * @createtime  2021-10-01 20:57:38
 * @comment     项目公共访问地址
 */
@RestController
@RequestMapping(value = "/api/v1/devops/projectPuburl/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="项目公共访问地址管理",value ="项目公共访问地址相关的操作")
public class ProjectPuburlApi extends TokenApi {
    @Autowired
    IProjectPuburlService projectPuburlService;

    @ApiOperation(value = "获取项目公共访问地址列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid ProjectPuburlListReq listReq){
        ProjectPuburlListResp resp = new ProjectPuburlListResp();
        resp.setTotalCount(projectPuburlService.queryCountBySearch(listReq.getSearch()));
        resp.setProjectPuburls(projectPuburlService.queryListViewBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定项目公共访问地址")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "项目公共访问地址id不能为空") String id){
        BaseResponse response=new BaseResponse();

        ProjectPuburlView projectPuburlView = projectPuburlService.queryModelView(id);
        response.put("projectPuburl",projectPuburlView);

        return response;
    }

    @ApiOperation(value = "搜索项目公共访问地址数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(ProjectPuburlSearch search){
        BaseResponse response=new BaseResponse();

        int count =  projectPuburlService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个项目公共访问地址")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "项目公共访问地址id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  projectPuburlService.deleteModel(id);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个项目公共访问地址")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveProjectPuburlReq saveProjectPuburlReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveProjectPuburlReq.getId())){
            saveProjectPuburlReq.setId(PkIdUtils.getId());
            saveProjectPuburlReq.setModelStatus(ModelStatus.add);
        }else{
            saveProjectPuburlReq.setModelStatus(ModelStatus.update);
        }

        boolean result = projectPuburlService.applyChange(saveProjectPuburlReq);
        response.put("result",result);
        return response;
    }

}