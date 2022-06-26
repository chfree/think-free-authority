package com.cditer.free.message.apis;

import com.cditer.free.message.entity.model.MessageReceive;
import com.cditer.free.message.entity.viewmodel.MessageReceiveSearch;
import com.cditer.free.message.entity.viewmodel.MessageReceiveView;
import com.cditer.free.message.service.IMessageReceiveService;
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
 * @createtime  2022-06-26 17:07:07
 * @comment     消息接收人
 */
@RestController
@RequestMapping(value = "/api/v1/message/messageReceive/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="消息接收人管理",value ="消息接收人相关的操作")
public class MessageReceiveApi extends TokenApi {
    @Autowired
    private IMessageReceiveService messageReceiveService;

    @ApiOperation(value = "获取消息接收人列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<MessageReceiveSearch> req){
        int totalCount = messageReceiveService.queryCountBySearch(req.getData());
        List<MessageReceiveView> list = messageReceiveService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定消息接收人")
    @GetMapping("get")
    public BaseResponse<MessageReceive> get(@Valid @NotBlank(message = "消息接收人id不能为空") String id){
        MessageReceive messageReceive = messageReceiveService.queryModel(id);
        return BaseResponse.success(messageReceive);
    }

    @ApiOperation(value = "搜索消息接收人数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<MessageReceiveSearch> req){
        int count =  messageReceiveService.queryCountBySearch(req.getData());
        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个消息接收人")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "消息接收人id不能为空")String id){
        boolean result =  messageReceiveService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个消息接收人")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<MessageReceive> req){
        boolean result = messageReceiveService.saveMessageReceive(req.getData());

        return BaseResponse.success(result);
    }

}
