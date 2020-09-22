package com.tennetcn.free.authority.apis;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.authority.data.entity.apimodel.filetemplate.FileTemplateListReq;
import com.tennetcn.free.authority.data.entity.apimodel.filetemplate.FileTemplateListResp;
import com.tennetcn.free.authority.data.entity.apimodel.filetemplate.SaveFileTemplateReq;
import com.tennetcn.free.authority.data.entity.model.FileTemplate;
import com.tennetcn.free.authority.data.entity.viewmodel.FileTemplateSearch;
import com.tennetcn.free.authority.service.IFileBsnService;
import com.tennetcn.free.authority.service.IFileTemplateService;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.core.util.PkIdUtils;
import com.tennetcn.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-15 08:43:41
 * @comment     文件模板表
 */
@RestController
@RequestMapping(value = "/api/v1/authority/fileTemplate/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="文件模板表管理",value ="文件模板表相关的操作")
public class FileTemplateApi extends AuthorityApi {
    @Autowired
    IFileTemplateService fileTemplateService;

    @ApiOperation(value = "获取文件模板表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid FileTemplateListReq listReq){
        FileTemplateListResp resp = new FileTemplateListResp();
        resp.setTotalCount(fileTemplateService.queryCountBySearch(listReq.getSearch()));
        resp.setFileTemplates(fileTemplateService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定文件模板表")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "文件模板表id不能为空") String id){
        BaseResponse response=new BaseResponse();

        FileTemplate fileTemplate = fileTemplateService.queryModel(id);
        response.put("fileTemplate",fileTemplate);

        return response;
    }

    @ApiOperation(value = "搜索文件模板表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(FileTemplateSearch search){
        BaseResponse response=new BaseResponse();

        int count =  fileTemplateService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个文件模板表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "文件模板表id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  fileTemplateService.deleteFileTemplate(id);

        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个文件模板表")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveFileTemplateReq saveFileTemplateReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveFileTemplateReq.getId())){
            saveFileTemplateReq.setId(PkIdUtils.getId());
            saveFileTemplateReq.setModelStatus(ModelStatus.add);
        }else{
            saveFileTemplateReq.setModelStatus(ModelStatus.update);
        }

        saveFileTemplateReq.setUploadDate(DateUtil.date());
        saveFileTemplateReq.setUploadUserId(getCurrentLogin().getId());
        saveFileTemplateReq.setUploadUserName(getCurrentLogin().getName());

        boolean result = fileTemplateService.applyChange(saveFileTemplateReq);
        response.put("result",result);
        response.put("id",saveFileTemplateReq.getId());

        return response;
    }
}