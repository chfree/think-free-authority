package com.cditer.free.behavior.apis;

import com.cditer.free.behavior.entity.model.WebVisitLog;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogSearch;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogView;
import com.cditer.free.behavior.service.IWebVisitLogService;
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
 * @createtime  2022-07-02 22:16:01
 * @comment     web访问日志表
 */
@RestController
@RequestMapping(value = "/api/v1/behavior/webVisitLog/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="web访问日志表管理",value ="web访问日志表相关的操作")
public class WebVisitLogApi extends TokenApi {
    @Autowired
    private IWebVisitLogService webVisitLogService;

    @ApiOperation(value = "获取web访问日志表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<WebVisitLogSearch> req){
        int totalCount = webVisitLogService.queryCountBySearch(req.getData());
        List<WebVisitLogView> list = webVisitLogService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定web访问日志表")
    @GetMapping("get")
    public BaseResponse<WebVisitLog> get(@Valid @NotBlank(message = "web访问日志表id不能为空") String id){
        WebVisitLog webVisitLog = webVisitLogService.queryModel(id);
        return BaseResponse.success(webVisitLog);
    }

    @ApiOperation(value = "搜索web访问日志表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<WebVisitLogSearch> req){
        int count =  webVisitLogService.queryCountBySearch(req.getData());
        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个web访问日志表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "web访问日志表id不能为空")String id){
        boolean result =  webVisitLogService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个web访问日志表")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<WebVisitLog> req){
        boolean result = webVisitLogService.saveWebVisitLog(req.getData());

        return BaseResponse.success(result);
    }

}