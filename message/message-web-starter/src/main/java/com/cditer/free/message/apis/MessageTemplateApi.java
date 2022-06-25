package com.cditer.free.message.apis;

import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.message.entity.viewmodel.MessageTemplateSearch;
import com.cditer.free.message.entity.viewmodel.MessageTemplateView;
import com.cditer.free.message.service.IMessageTemplateService;
import com.cditer.free.security.baseapi.TokenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-25 10:48:19
 * @comment     消息模板表
 */
@RestController
@RequestMapping(value = "/api/v1/xxxx/messageTemplate/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="消息模板表管理",value ="消息模板表相关的操作")
public class MessageTemplateApi extends TokenApi {
    @Autowired
    IMessageTemplateService messageTemplateService;

    @ApiOperation(value = "获取消息模板表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<MessageTemplateSearch> req){
        BaseResponse<List<MessageTemplateView>> resp = new BaseResponse<>();
//        MessageTemplateListResp resp = new MessageTemplateListResp();
//        resp.setTotalCount(messageTemplateService.queryCountBySearch(req.getSearch()));
//        resp.setMessageTemplates(messageTemplateService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }
//
//    @ApiOperation(value = "获取指定消息模板表")
//    @GetMapping("get")
//    public BaseResponse get(@Valid @NotBlank(message = "消息模板表id不能为空") String id){
//        BaseResponse response=new BaseResponse();
//
//        MessageTemplate messageTemplate = messageTemplateService.queryModel(id);
//        response.put("messageTemplate",messageTemplate);
//
//        return response;
//    }
//
//    @ApiOperation(value = "搜索消息模板表数量")
//    @PostMapping("countSearch")
//    public BaseResponse countSearch(MessageTemplateSearch search){
//        BaseResponse response=new BaseResponse();
//
//        int count =  messageTemplateService.queryCountBySearch(search);
//        response.put("count",count);
//
//        return response;
//    }
//
//    @ApiOperation(value = "删除一个消息模板表")
//    @PostMapping("delete")
//    public BaseResponse delete(@Valid @NotBlank(message = "消息模板表id不能为空")String id){
//        BaseResponse response=new BaseResponse();
//
//        boolean result =  messageTemplateService.deleteModel(id);
//        response.put("result",result);
//
//        return response;
//    }
//
//    @ApiOperation(value = "保存一个消息模板表")
//    @PostMapping("save")
//    public BaseResponse save(@Valid SaveMessageTemplateReq saveMessageTemplateReq){
//        BaseResponse response = new BaseResponse();
//        if(StringUtils.isEmpty(saveMessageTemplateReq.getId())){
//            saveMessageTemplateReq.setId(PkIdUtils.getId());
//            saveMessageTemplateReq.setModelStatus(ModelStatus.add);
//        }else{
//            saveMessageTemplateReq.setModelStatus(ModelStatus.update);
//        }
//
//        boolean result = messageTemplateService.applyChange(saveMessageTemplateReq);
//        response.put("result",result);
//        return response;
//    }

}