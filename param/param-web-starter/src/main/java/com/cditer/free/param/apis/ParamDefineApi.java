package com.cditer.free.param.apis;

import cn.hutool.core.util.IdUtil;
import com.cditer.free.param.data.entity.apimodel.paramdefine.ParamDefineListReq;
import com.cditer.free.param.data.entity.apimodel.paramdefine.ParamDefineListResp;
import com.cditer.free.param.data.entity.apimodel.paramdefine.SaveParamDefineReq;
import com.cditer.free.param.logical.service.IParamDefineService;
import com.cditer.free.param.data.entity.model.ParamDefine;
import com.cditer.free.param.data.entity.viewmodel.ParamDefineSearch;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:44
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/param/paramDefine/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="参数定义",value ="参数定义相关的操作")
public class ParamDefineApi extends AuthorityApi {

    @Autowired
    IParamDefineService paramDefineService;

    @ApiOperation(value = "获取参数定义列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody  @Valid ParamDefineListReq listReq){
        ParamDefineListResp resp = new ParamDefineListResp();
        resp.setTotalCount(paramDefineService.queryCountBySearch(listReq.getSearch()));
        resp.setParamDefines(paramDefineService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定参数定义")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "参数定义id不能为空") String id){
        BaseResponse response=new BaseResponse();

        ParamDefine paramDefine = paramDefineService.queryModel(id);
        response.put("paramDefine",paramDefine);

        return response;
    }

    @ApiOperation(value = "搜索参数定义数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(ParamDefineSearch search){
        BaseResponse response=new BaseResponse();

        int count =  paramDefineService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定参数定义")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个参数定义")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveParamDefineReq saveParamDefineReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveParamDefineReq.getId())){
            saveParamDefineReq.setId(IdUtil.randomUUID());
            saveParamDefineReq.setModelStatus(ModelStatus.add);
        }else{
            saveParamDefineReq.setModelStatus(ModelStatus.update);
        }

        boolean result = paramDefineService.applyChange(saveParamDefineReq);
        response.put("result",result);

        return response;
    }
}
