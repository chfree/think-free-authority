package com.cditer.free.authority.apis;

import com.cditer.free.authority.data.entity.apimodel.group.GroupListReq;
import com.cditer.free.authority.data.entity.apimodel.group.GroupListResp;
import com.cditer.free.authority.data.entity.apimodel.group.SaveGroupReq;
import com.cditer.free.authority.logical.service.IGroupService;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.authority.data.entity.viewmodel.GroupSearch;
import com.cditer.free.authority.data.entity.model.Group;
import com.cditer.free.security.webapi.AuthorityApi;
import com.cditer.free.core.message.web.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-05-31 12:56:33
 * @comment     权限组
 */
@RestController
@RequestMapping(value = "/api/v1/authority/group/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="权限组管理",value ="权限组相关的操作")
public class GroupApi extends AuthorityApi {
    @Autowired
    IGroupService groupService;

    @ApiOperation(value = "获取权限组列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid GroupListReq listReq){
        GroupListResp resp = new GroupListResp();
        resp.setTotalCount(groupService.queryCountBySearch(listReq.getSearch()));
        resp.setGroups(groupService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定权限组")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "权限组id不能为空") String id){
        BaseResponse response=new BaseResponse();

        Group group = groupService.queryModel(id);
        response.put("group",group);

        return response;
    }

    @ApiOperation(value = "搜索权限组数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(GroupSearch search){
        BaseResponse response=new BaseResponse();

        int count =  groupService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个权限组")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "权限组id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  groupService.deleteModel(id);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "保存一个权限组")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveGroupReq saveGroupReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveGroupReq.getId())){
            saveGroupReq.setId(PkIdUtils.getId());
            saveGroupReq.setModelStatus(ModelStatus.add);
        }else{
            saveGroupReq.setModelStatus(ModelStatus.update);
        }

        boolean result = groupService.applyChange(saveGroupReq);
        response.put("result",result);
        return response;
    }

}
