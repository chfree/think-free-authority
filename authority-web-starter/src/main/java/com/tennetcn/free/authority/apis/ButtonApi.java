package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.data.entity.apimodel.button.ButtonListReq;
import com.tennetcn.free.authority.data.entity.apimodel.button.ButtonListResp;
import com.tennetcn.free.authority.data.entity.apimodel.button.SaveButtonReq;
import com.tennetcn.free.authority.data.entity.model.Button;
import com.tennetcn.free.authority.service.IButtonService;
import com.tennetcn.free.authority.data.entity.viewmodel.ButtonSearch;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.webapi.AuthorityApi;
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
@RequestMapping(value = "/api/v1/authority/button/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="按钮管理",value ="按钮相关的操作")
public class ButtonApi extends AuthorityApi {

    @Autowired
    IButtonService buttonService;

    @ApiOperation(value = "获取按钮列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody  @Valid ButtonListReq listReq){
        ButtonListResp resp = new ButtonListResp();
        resp.setTotalCount(buttonService.queryCountBySearch(listReq.getSearch()));
        resp.setButtons(buttonService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定按钮")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "按钮id不能为空") String id){
        BaseResponse response=new BaseResponse();

        Button button = buttonService.queryModel(id);
        response.put("button",button);

        return response;
    }

    @ApiOperation(value = "搜索按钮数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(ButtonSearch search){
        BaseResponse response=new BaseResponse();

        int count =  buttonService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定按钮")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个按钮")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveButtonReq saveButtonReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveButtonReq.getId())){
            saveButtonReq.setId(IdUtil.randomUUID());
            saveButtonReq.setModelStatus(ModelStatus.add);
        }else{
            saveButtonReq.setModelStatus(ModelStatus.update);
        }

        boolean result = buttonService.applyChange(saveButtonReq);
        response.put("result",result);

        return response;
    }
}
