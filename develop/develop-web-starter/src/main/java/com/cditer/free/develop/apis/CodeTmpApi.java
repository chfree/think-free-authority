package com.cditer.free.develop.apis;


import cn.hutool.core.util.IdUtil;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.develop.data.entity.apimodel.codetmp.CodeTmpListReq;
import com.cditer.free.develop.data.entity.apimodel.codetmp.CodeTmpListResp;
import com.cditer.free.develop.data.entity.apimodel.codetmp.SaveCodeTmpReq;
import com.cditer.free.develop.data.entity.model.CodeTmp;
import com.cditer.free.develop.service.ICodeTmpService;
import com.cditer.free.develop.data.entity.viewmodel.CodeTmpSearch;
import com.cditer.free.coreweb.security.AuthorityApi;
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
 * @createtime  2020-02-05 16:46:13
 * @comment     代码模板
 */
@RestController
@RequestMapping(value = "/api/v1/develop/codeTmp/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="代码模板管理",value ="代码模板相关的操作")
public class CodeTmpApi extends AuthorityApi {
    @Autowired
    ICodeTmpService codeTmpService;

    @ApiOperation(value = "获取代码模板列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid CodeTmpListReq listReq){
        CodeTmpListResp resp = new CodeTmpListResp();

        CodeTmpSearch search = listReq.getSearch();
        search.setCreateUserId(getLoginId());

        resp.setTotalCount(codeTmpService.queryCountBySearch(search));
        resp.setCodeTmps(codeTmpService.queryListBySearch(search,listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定代码模板")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "代码模板id不能为空") String id){
        BaseResponse response=new BaseResponse();

        CodeTmp codeTmp = codeTmpService.queryModel(id);
        response.put("codeTmp",codeTmp);

        return response;
    }

    @ApiOperation(value = "搜索代码模板数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(CodeTmpSearch search){
        BaseResponse response=new BaseResponse();

        search.setCreateUserId(getLoginId());

        int count =  codeTmpService.queryCountByCheck(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "更新公开状态")
    @PostMapping("updatePub")
    public BaseResponse updatePub(@Valid @NotBlank(message = "代码模板id不能为空")String id,@Valid @NotBlank(message = "模板状态不能为空")String pub){
        BaseResponse response=new BaseResponse();

        response.put("result",codeTmpService.updatePub(id,pub));

        return response;
    }

    @ApiOperation(value = "删除一个模板")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "代码模板id不能为空")String id){
        BaseResponse response=new BaseResponse();

        response.put("result",codeTmpService.deleteModel(id));

        return response;
    }

    @ApiOperation(value = "保存一个代码模板")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveCodeTmpReq saveCodeTmpReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveCodeTmpReq.getId())){
            saveCodeTmpReq.setId(IdUtil.randomUUID());
            saveCodeTmpReq.setCreateUserId(getLoginId());
            saveCodeTmpReq.setCreateUserName(getLoginName());
            saveCodeTmpReq.setModelStatus(ModelStatus.add);
        }else{
            saveCodeTmpReq.setModelStatus(ModelStatus.update);
        }

        boolean result = codeTmpService.applyChange(saveCodeTmpReq);
        response.put("result",result);
        return response;
    }

}