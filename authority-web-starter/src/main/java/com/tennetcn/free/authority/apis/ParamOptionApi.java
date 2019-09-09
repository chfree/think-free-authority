package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.paramdefine.ParamDefineListReq;
import com.tennetcn.free.authority.apimodel.paramdefine.ParamDefineListResp;
import com.tennetcn.free.authority.apimodel.paramdefine.SaveParamDefineReq;
import com.tennetcn.free.authority.apimodel.paramoption.ParamOptionListReq;
import com.tennetcn.free.authority.apimodel.paramoption.ParamOptionListResp;
import com.tennetcn.free.authority.apimodel.paramoption.SaveParamOptionReq;
import com.tennetcn.free.authority.model.ParamDefine;
import com.tennetcn.free.authority.model.ParamOption;
import com.tennetcn.free.authority.service.IParamDefineService;
import com.tennetcn.free.authority.service.IParamOptionService;
import com.tennetcn.free.authority.viewmodel.ParamDefineSearch;
import com.tennetcn.free.authority.viewmodel.ParamOptionSearch;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.webapi.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:44
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/authority/paramOption/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="参数选项",value ="参数选项相关的操作")
public class ParamOptionApi extends AuthorityApi {

    @Autowired
    IParamOptionService paramOptionService;

    @ApiOperation(value = "获取参数选项列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody  @Valid ParamOptionListReq listReq){
        ParamOptionListResp resp = new ParamOptionListResp();
        resp.setTotalCount(paramOptionService.queryCountBySearch(listReq.getSearch()));
        resp.setParamOptions(paramOptionService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定参数选项")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "参数选项不能为空") String id){
        BaseResponse response=new BaseResponse();

        ParamOption paramOption = paramOptionService.queryModel(id);
        response.put("paramOption",paramOption);

        return response;
    }

    @ApiOperation(value = "搜索参数选项数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(ParamOptionSearch search){
        BaseResponse response=new BaseResponse();

        int count =  paramOptionService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定参数选项")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个参数选项")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveParamOptionReq saveParamOptionReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveParamOptionReq.getId())){
            saveParamOptionReq.setId(IdUtil.randomUUID());
            saveParamOptionReq.setModelStatus(ModelStatus.add);
        }else{
            saveParamOptionReq.setModelStatus(ModelStatus.update);
        }

        boolean result = paramOptionService.applyChange(saveParamOptionReq);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "根据参数名称获取参数选项")
    @PostMapping("listByDefineName")
    public BaseResponse listByDefineName(@Valid @NotBlank(message = "参数名称不能为空") String defineName){
        BaseResponse response=new BaseResponse();

        response.put("paramOptions",paramOptionService.queryListByDefineName(defineName));

        return response;
    }

    @ApiOperation(value = "根据参数名称获取参数选项")
    @PostMapping("listByDefineNames")
    public BaseResponse listByDefineNames(@RequestBody List<String> defineNames){
        BaseResponse response=new BaseResponse();

        response.put("paramOptionMap",paramOptionService.queryListGroupByDefineNames(defineNames));

        return response;
    }
}
