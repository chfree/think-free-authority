package com.cditer.free.behavior.apis;

import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitSearch;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitView;
import com.cditer.free.behavior.service.IWebVisitLimitService;
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
 * @createtime  2022-07-02 22:16:40
 * @comment     web访问控制表
 */
@RestController
@RequestMapping(value = "/api/v1/behavior/webVisitLimit/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="web访问控制表管理",value ="web访问控制表相关的操作")
public class WebVisitLimitApi extends TokenApi {
    @Autowired
    private IWebVisitLimitService webVisitLimitService;

    @ApiOperation(value = "获取web访问控制表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<WebVisitLimitSearch> req){
        int totalCount = webVisitLimitService.queryCountBySearch(req.getData());
        List<WebVisitLimitView> list = webVisitLimitService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定web访问控制表")
    @GetMapping("get")
    public BaseResponse<WebVisitLimit> get(@Valid @NotBlank(message = "web访问控制表id不能为空") String id){
        WebVisitLimit webVisitLimit = webVisitLimitService.queryModelViewById(id);
        return BaseResponse.success(webVisitLimit);
    }

    @ApiOperation(value = "搜索web访问控制表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<WebVisitLimitSearch> req){
        int count =  webVisitLimitService.queryCountBySearch(req.getData());
        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个web访问控制表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "web访问控制表id不能为空")String id){
        boolean result =  webVisitLimitService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个web访问控制表")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<WebVisitLimit> req){
        boolean result = webVisitLimitService.saveWebVisitLimit(req.getData());

        return BaseResponse.success(result);
    }

}
