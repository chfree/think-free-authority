package com.cditer.free.behavior.apis;

import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogView;
import com.cditer.free.behavior.service.IDataEditLogService;
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
 * @createtime  2022-07-02 21:48:42
 * @comment     数据修改记录
 */
@RestController
@RequestMapping(value = "/api/v1/behavior/dataEditLog/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="数据修改记录管理",value ="数据修改记录相关的操作")
public class DataEditLogApi extends TokenApi {
    @Autowired
    private IDataEditLogService dataEditLogService;

    @ApiOperation(value = "获取数据修改记录列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<DataEditLogSearch> req){
        int totalCount = dataEditLogService.queryCountBySearch(req.getData());
        List<DataEditLogView> list = dataEditLogService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定数据修改记录")
    @GetMapping("get")
    public BaseResponse<DataEditLog> get(@Valid @NotBlank(message = "数据修改记录id不能为空") String id){
        DataEditLog dataEditLog = dataEditLogService.queryModel(id);
        return BaseResponse.success(dataEditLog);
    }

    @ApiOperation(value = "搜索数据修改记录数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<DataEditLogSearch> req){
        int count =  dataEditLogService.queryCountBySearch(req.getData());
        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个数据修改记录")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "数据修改记录id不能为空")String id){
        boolean result =  dataEditLogService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个数据修改记录")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<DataEditLog> req){
        boolean result = dataEditLogService.saveDataEditLog(req.getData());

        return BaseResponse.success(result);
    }

}
