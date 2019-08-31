package com.tennetcn.free.authority.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.tennetcn.free.authority.dao.IUserDao;
import com.tennetcn.free.authority.model.User;
import com.tennetcn.free.authority.service.IUserService;
import com.tennetcn.free.authority.viewmodel.UserSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.data.message.DaoBaseRuntimeException;
import com.tennetcn.free.data.message.PagerModel;
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
    IUserDao userDao;

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

    private String passwordFormat(String password){
        return SecureUtil.md5(password);
    }

    @Override
    public User queryModelByAccount(String account) {
        return userDao.queryModelByAccount(account);
    }

    @Override
    public boolean applyChange(User user) throws DaoBaseRuntimeException {
        if(ModelStatus.add.equals(user.getModelStatus())){
            user.setPassword("000000");
        }
        return super.applyChange(user);
    }
}
