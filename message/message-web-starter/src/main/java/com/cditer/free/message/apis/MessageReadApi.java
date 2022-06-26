package com.cditer.free.message.apis;

import com.cditer.free.message.entity.model.MessageRead;
import com.cditer.free.message.entity.viewmodel.MessageReadSearch;
import com.cditer.free.message.entity.viewmodel.MessageReadView;
import com.cditer.free.message.service.IMessageReadService;
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
 * @createtime  2022-06-26 17:12:04
 * @comment     消息读记录
 */
@RestController
@RequestMapping(value = "/api/v1/message/messageRead/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="消息读记录管理",value ="消息读记录相关的操作")
public class MessageReadApi extends TokenApi {
    @Autowired
    private IMessageReadService messageReadService;

    @ApiOperation(value = "获取消息读记录列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<MessageReadSearch> req){
        int totalCount = messageReadService.queryCountBySearch(req.getData());
        List<MessageReadView> list = messageReadService.queryListViewBySearch(req.getData(), req.getPager());

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定消息读记录")
    @GetMapping("get")
    public BaseResponse<MessageRead> get(@Valid @NotBlank(message = "消息读记录id不能为空") String id){
        MessageRead messageRead = messageReadService.queryModel(id);
        return BaseResponse.success(messageRead);
    }

    @ApiOperation(value = "搜索消息读记录数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<MessageReadSearch> req){
        int count =  messageReadService.queryCountBySearch(req.getData());
        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个消息读记录")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "消息读记录id不能为空")String id){
        boolean result =  messageReadService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个消息读记录")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<MessageRead> req){
        boolean result = messageReadService.saveMessageRead(req.getData());

        return BaseResponse.success(result);
    }

}
