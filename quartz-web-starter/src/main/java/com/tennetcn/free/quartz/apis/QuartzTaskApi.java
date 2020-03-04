package com.tennetcn.free.quartz.apis;


import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.core.util.PkIdUtils;
import com.tennetcn.free.quartz.apimodel.quartztask.QuartzTaskListReq;
import com.tennetcn.free.quartz.apimodel.quartztask.QuartzTaskListResp;
import com.tennetcn.free.quartz.apimodel.quartztask.SaveQuartzTaskReq;
import com.tennetcn.free.quartz.logical.model.QuartzTask;
import com.tennetcn.free.quartz.logical.service.IQuartzTaskService;
import com.tennetcn.free.quartz.logical.viewmodel.QuartzTaskSearch;
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
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-03-01 11:28:37
 * @comment     定时任务表
 */
@RestController
@RequestMapping(value = "/api/v1/quartz/task/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="定时任务表管理",value ="定时任务表相关的操作")
public class QuartzTaskApi extends AuthorityApi {
    @Autowired
    IQuartzTaskService quartzTaskService;

    @ApiOperation(value = "获取定时任务列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid QuartzTaskListReq listReq){
        QuartzTaskListResp resp = new QuartzTaskListResp();
        resp.setTotalCount(quartzTaskService.queryCountBySearch(listReq.getSearch()));
        resp.setQuartzTasks(quartzTaskService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定定时任务")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "定时任务表id不能为空") String id){
        BaseResponse response=new BaseResponse();

        QuartzTask quartzTask = quartzTaskService.queryModel(id);
        response.put("quartzTask",quartzTask);

        return response;
    }

    @ApiOperation(value = "根据名称获取指定定时任务")
    @GetMapping("getByName")
    public BaseResponse getByName(@Valid @NotBlank(message = "任务名称不能为空") String name){
        BaseResponse response=new BaseResponse();

        QuartzTask quartzTask = quartzTaskService.queryModelByName(name);
        response.put("quartzTask",quartzTask);

        return response;
    }

    @ApiOperation(value = "搜索定时任务表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(QuartzTaskSearch search){
        BaseResponse response=new BaseResponse();

        int count =  quartzTaskService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个定时任务表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "定时任务表id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  quartzTaskService.deleteModel(id);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个定时任务表")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveQuartzTaskReq saveQuartzTaskReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveQuartzTaskReq.getId())){
            saveQuartzTaskReq.setId(PkIdUtils.getId());
            saveQuartzTaskReq.setModelStatus(ModelStatus.add);
        }else{
            saveQuartzTaskReq.setModelStatus(ModelStatus.update);
        }

        boolean result = quartzTaskService.applyChange(saveQuartzTaskReq);
        response.put("result",result);
        return response;
    }

}