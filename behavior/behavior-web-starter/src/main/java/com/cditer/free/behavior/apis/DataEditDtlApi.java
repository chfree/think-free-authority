package com.cditer.free.behavior.apis;

import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlView;
import com.cditer.free.behavior.service.IDataEditDtlService;
import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.core.message.web.BaseRequest;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.security.baseapi.TokenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 21:53:50
 * @comment     数据修改详情
 */
@RestController
@RequestMapping(value = "/api/v1/behavior/dataEditDtl/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="数据修改详情管理",value ="数据修改详情相关的操作")
public class DataEditDtlApi extends TokenApi {
    @Autowired
    private IDataEditDtlService dataEditDtlService;

    @ApiOperation(value = "获取数据修改详情列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<DataEditDtlSearch> req){
        int totalCount = dataEditDtlService.queryCountBySearch(req.getData());
        List<DataEditDtlView> list = dataEditDtlService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定数据修改详情")
    @GetMapping("get")
    public BaseResponse<DataEditDtl> get(@Valid @NotBlank(message = "数据修改详情id不能为空") String id){
        DataEditDtl dataEditDtl = dataEditDtlService.queryModelViewById(id);
        return BaseResponse.success(dataEditDtl);
    }

    @ApiOperation(value = "搜索数据修改详情数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<DataEditDtlSearch> req){
        int count =  dataEditDtlService.queryCountBySearch(req.getData());
        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个数据修改详情")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "数据修改详情id不能为空")String id){
        boolean result =  dataEditDtlService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个数据修改详情")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<DataEditDtl> req){
        boolean result = dataEditDtlService.saveDataEditDtl(req.getData());

        return BaseResponse.success(result);
    }

}