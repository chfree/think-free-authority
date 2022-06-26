package com.cditer.free.message.apis;

import com.cditer.free.message.entity.model.MessageInfo;
import com.cditer.free.message.entity.viewmodel.MessageInfoSearch;
import com.cditer.free.message.entity.viewmodel.MessageInfoView;
import com.cditer.free.message.service.IMessageInfoService;
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
 * @createtime  2022-06-26 16:55:04
 * @comment     消息表
 */
@RestController
@RequestMapping(value = "/api/v1/message/messageInfo/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="消息表管理",value ="消息表相关的操作")
public class MessageInfoApi extends TokenApi {
    @Autowired
    private IMessageInfoService messageInfoService;

    @ApiOperation(value = "获取消息表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<MessageInfoSearch> req){
        int totalCount = messageInfoService.queryCountBySearch(req.getData());
        List<MessageInfoView> list = messageInfoService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定消息表")
    @GetMapping("get")
    public BaseResponse<MessageInfo> get(@Valid @NotBlank(message = "消息表id不能为空") String id){
        MessageInfo messageInfo = messageInfoService.queryModel(id);
        return BaseResponse.success(messageInfo);
    }

    @ApiOperation(value = "搜索消息表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<MessageInfoSearch> req){
        int count =  messageInfoService.queryCountBySearch(req.getData());

        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个消息表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "消息表id不能为空")String id){
        boolean result =  messageInfoService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个消息表")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<MessageInfo> req){
        boolean result = messageInfoService.saveMessageInfo(req.getData());

        return BaseResponse.success(result);
    }

}
