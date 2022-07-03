package com.cditer.free.behavior.apis;

import com.cditer.free.behavior.entity.model.DataEntityInfo;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoView;
import com.cditer.free.behavior.service.IDataEntityInfoService;
import com.cditer.free.security.baseapi.TokenApi;
import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.core.message.web.BaseRequest;
import com.cditer.free.core.message.web.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 21:59:28
 * @comment     数据实体信息
 */
@RestController
@RequestMapping(value = "/api/v1/behavior/dataEntityInfo/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="数据实体信息管理",value ="数据实体信息相关的操作")
public class DataEntityInfoApi extends TokenApi {
    @Autowired
    private IDataEntityInfoService dataEntityInfoService;

    @ApiOperation(value = "获取数据实体信息列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<DataEntityInfoSearch> req){
        int totalCount = dataEntityInfoService.queryCountBySearch(req.getData());
        List<DataEntityInfoView> list = dataEntityInfoService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定数据实体信息")
    @GetMapping("get")
    public BaseResponse<DataEntityInfo> get(@Valid @NotBlank(message = "数据实体信息id不能为空") String id){
        DataEntityInfo dataEntityInfo = dataEntityInfoService.queryModelViewById(id);
        return BaseResponse.success(dataEntityInfo);
    }

    @ApiOperation(value = "搜索数据实体信息数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<DataEntityInfoSearch> req){
        int count =  dataEntityInfoService.queryCountBySearch(req.getData());
        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个数据实体信息")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "数据实体信息id不能为空")String id){
        boolean result =  dataEntityInfoService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个数据实体信息")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<DataEntityInfo> req){
        boolean result = dataEntityInfoService.saveDataEntityInfo(req.getData());

        return BaseResponse.success(result);
    }

}
