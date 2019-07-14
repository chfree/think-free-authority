package com.tennetcn.free.authority.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.tennetcn.free.authority.mapper.IUserMapper;
import com.tennetcn.free.authority.model.User;
import com.tennetcn.free.authority.service.IUserService;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 22:59
 * @comment
 */

@Component
public class UserServiceImpl extends SuperService<User> implements IUserService {

    @Autowired
    IUserMapper userMapper;

    @Override
    public List<User> queryListMPByIds(List<String> ids) {
        return userMapper.queryListMPByIds(ids);
    }

    @Override
    public String getLoginUserNamesByIds(List<String> ids) {
        return userMapper.getLoginUserNamesByIds(ids);
    }

    @Override
    public Boolean queryBylogin(String username, String password) {
        String md5Password = SecureUtil.md5(password);
        ISqlExpression loginSql= SqlExpressionFactory.createExpression();
        loginSql.selectCount()
                .from(User.class)
                .andEq("account",username)
                .andEq("password",md5Password);

        return queryCount(loginSql)>0;
    }
}
