package com.cditer.free.authority.logical.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.cditer.free.authority.data.entity.viewmodel.UserView;
import com.cditer.free.authority.logical.dao.IUserDao;
import com.cditer.free.authority.data.entity.apimodel.user.SaveUserReq;
import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.authority.data.entity.model.UserGroup;
import com.cditer.free.authority.data.entity.model.UserRole;
import com.cditer.free.authority.logical.service.IUserGroupService;
import com.cditer.free.authority.logical.service.IUserRoleService;
import com.cditer.free.authority.logical.service.IUserService;
import com.cditer.free.authority.data.entity.viewmodel.UserSearch;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

@Component
public class UserServiceImpl extends SuperService<User> implements IUserService {

    private final String defaultPwd = "000000";

    @Autowired
    IUserDao userDao;

    @Autowired
    IUserRoleService userRoleService;

    @Autowired
    IUserGroupService userGroupService;

    @Override
    public int queryCountBySearch(UserSearch search) {
        return userDao.queryCountBySearch(search);
    }

    @Override
    public List<User> queryListBySearch(UserSearch search, PagerModel pagerModel) {
        return userDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public User queryModelByLogin(String account, String password) {
        return userDao.queryModelByLogin(account,passwordFormat(password));
    }

    @Override
    public String passwordFormat(String password){
        return SecureUtil.md5(password);
    }

    @Override
    public boolean saveUser(SaveUserReq userReq) {
        if(ModelStatus.add.equals(userReq.getModelStatus())){
            if(StringUtils.isEmpty(userReq.getPassword())){
                userReq.setPassword(passwordFormat(defaultPwd));
            }
        }
        if(!this.applyChange(userReq)) {
            return false;
        }
        if(!saveUserGroup(userReq.getId(),userReq.getGroupIds())){
            return false;
        }
        if(!saveUserRole(userReq.getId(),userReq.getRoleIds())){
            return false;
        }
        return true;
    }

    private boolean saveUserGroup(String userId,List<String> groupIds){
        if(groupIds==null||groupIds.isEmpty()){
            userGroupService.deleteByUserId(userId);
            return true;
        }
        List<UserGroup> userGroups = groupIds.stream().map(group -> UserGroup.builder().groupId(group).userId(userId).id(IdUtil.randomUUID()).build()).collect(Collectors.toList());

        return userGroupService.saveUserGroups(userId, userGroups);
    }

    private boolean saveUserRole(String userId,List<String> roleIds){
        if(roleIds==null||roleIds.isEmpty()){
            userRoleService.deleteByUserId(userId);
            return true;
        }
        List<UserRole> userRoles = roleIds.stream().map(role -> UserRole.builder().roleId(role).userId(userId).id(IdUtil.randomUUID()).build()).collect(Collectors.toList());

        return userRoleService.saveUserRoles(userId, userRoles);
    }

    @Override
    public User queryModelByAccount(String account) {
        return userDao.queryModelByAccount(account);
    }

    @Override
    public List<UserView> queryViewListBySearch(UserSearch search, PagerModel pagerModel) {
        return userDao.queryViewListBySearch(search,pagerModel);
    }

    @Override
    public UserView queryViewModelById(String id) {
        return userDao.queryViewModelById(id);
    }

    @Override
    public int queryCountByLoginUserSearch(UserSearch search) {
        return userDao.queryCountByLoginUserSearch(search);
    }

    @Override
    public User queryModelBySearch(String userId) {
        User search = new User();
        search.setId(userId);

        return queryModel(search);
    }
}
