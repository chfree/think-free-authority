package com.cditer.free.quartz.apis;


import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.quartz.apimodel.quartztasklog.QuartzTaskLogListReq;
import com.cditer.free.quartz.apimodel.quartztasklog.QuartzTaskLogListResp;
import com.cditer.free.quartz.logical.model.QuartzTaskLog;
import com.cditer.free.quartz.logical.service.IQuartzTaskLogService;
import com.cditer.free.quartz.logical.viewmodel.QuartzTaskLogSearch;
import com.cditer.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-03-01 11:43:32
 * @comment     定时任务日志表
 */
@RestController
@RequestMapping(value = "/api/v1/quartz/taskLog/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="定时任务日志表管理",value ="定时任务日志表相关的操作")
public class QuartzTaskLogApi extends AuthorityApi {

    @Autowired
    IQuartzTaskLogService quartzTaskLogService;

    @ApiOperation(value = "获取定时任务日志表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid QuartzTaskLogListReq listReq){
        QuartzTaskLogListResp resp = new QuartzTaskLogListResp();
        resp.setTotalCount(quartzTaskLogService.queryCountBySearch(listReq.getSearch()));
        resp.setQuartzTaskLogs(quartzTaskLogService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定定时任务日志表")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "定时任务日志表id不能为空") String id){
        BaseResponse response=new BaseResponse();

        QuartzTaskLog quartzTaskLog = quartzTaskLogService.queryModel(id);
        response.put("quartzTaskLog",quartzTaskLog);

        return response;
    }

    @ApiOperation(value = "搜索定时任务日志表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(QuartzTaskLogSearch search){
        BaseResponse response=new BaseResponse();

        int count =  quartzTaskLogService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }
}
