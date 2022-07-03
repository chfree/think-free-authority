package com.cditer.free.behavior.apis;

import com.cditer.free.behavior.entity.model.DataPropertyMapping;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingSearch;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingView;
import com.cditer.free.behavior.service.IDataPropertyMappingService;
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
 * @createtime  2022-07-02 22:01:17
 * @comment     属性映射表
 */
@RestController
@RequestMapping(value = "/api/v1/behavior/dataPropertyMapping/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="属性映射表管理",value ="属性映射表相关的操作")
public class DataPropertyMappingApi extends TokenApi {
    @Autowired
    private IDataPropertyMappingService dataPropertyMappingService;

    @ApiOperation(value = "获取属性映射表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<DataPropertyMappingSearch> req){
        int totalCount = dataPropertyMappingService.queryCountBySearch(req.getData());
        List<DataPropertyMappingView> list = dataPropertyMappingService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定属性映射表")
    @GetMapping("get")
    public BaseResponse<DataPropertyMapping> get(@Valid @NotBlank(message = "属性映射表id不能为空") String id){
        DataPropertyMapping dataPropertyMapping = dataPropertyMappingService.queryModelViewById(id);
        return BaseResponse.success(dataPropertyMapping);
    }

    @ApiOperation(value = "搜索属性映射表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<DataPropertyMappingSearch> req){
        int count =  dataPropertyMappingService.queryCountBySearch(req.getData());
        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个属性映射表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "属性映射表id不能为空")String id){
        boolean result =  dataPropertyMappingService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个属性映射表")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<DataPropertyMapping> req){
        boolean result = dataPropertyMappingService.saveDataPropertyMapping(req.getData());

        return BaseResponse.success(result);
    }

}