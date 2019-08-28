package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.button.ButtonListReq;
import com.tennetcn.free.authority.apimodel.button.ButtonListResp;
import com.tennetcn.free.authority.apimodel.button.SaveButtonReq;
import com.tennetcn.free.authority.apimodel.paramdefine.ParamDefineListReq;
import com.tennetcn.free.authority.apimodel.paramdefine.ParamDefineListResp;
import com.tennetcn.free.authority.apimodel.paramdefine.SaveParamDefineReq;
import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.authority.model.ParamDefine;
import com.tennetcn.free.authority.service.IButtonService;
import com.tennetcn.free.authority.service.IParamDefineService;
import com.tennetcn.free.authority.viewmodel.ButtonSearch;
import com.tennetcn.free.authority.viewmodel.ParamDefineSearch;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.webapi.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/api/v1/authority/paramDefine/")
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
