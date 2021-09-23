package com.cditer.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.cditer.free.authority.data.entity.apimodel.usergroup.SaveUserGroupReq;
import com.cditer.free.authority.data.entity.model.UserGroup;
import com.cditer.free.authority.logical.service.IUserGroupService;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.web.security.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:45
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/userGroup/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="用户角色",value ="用户组相关的操作")
public class UserGroupApi extends AuthorityApi {

    @Autowired
    private IUserGroupService userGroupService;

    @ApiOperation(value = "根据用户id获取组信息")
    @GetMapping("listGroup")
    public BaseResponse listGroup(@Valid @NotBlank(message = "用户id不能为空") String userId){
        BaseResponse response = new BaseResponse();
        response.put("groups",userGroupService.queryListByUserId(userId));

        return response;
    }

    @ApiOperation(value = "保存用户角色")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid SaveUserGroupReq saveUserGroupReq){
        BaseResponse response = new BaseResponse();
        boolean result = userGroupService.saveUserGroups(saveUserGroupReq.getUserId(),getUserGroupFormat(saveUserGroupReq));
        response.put("result", result);

        return response;
    }

    private List<UserGroup> getUserGroupFormat(SaveUserGroupReq saveUserGroupReq){
        return saveUserGroupReq.getGroupIds().stream().map(groupId-> UserGroup.builder()
                .id(IdUtil.randomUUID())
                .userId(saveUserGroupReq.getUserId())
                .groupId(groupId)
                .build()
        ).collect(Collectors.toList());
    }
}
