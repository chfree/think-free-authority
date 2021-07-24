package com.cditer.free.authority.logical.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cditer.free.authority.logical.dao.IRoleFuncDao;
import com.cditer.free.authority.data.entity.model.RoleFunc;
import com.cditer.free.authority.logical.service.IRoleFuncService;
import com.cditer.free.authority.data.entity.viewmodel.RoleFuncSearch;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:14
 * @comment
 */

@Component
public class RoleFuncServiceImpl extends SuperService<RoleFunc> implements IRoleFuncService {

    @Autowired
    IRoleFuncDao roleFuncDao;

    @Override
    public boolean saveRoleFuncs(String roleId, List<RoleFunc> roleFuncs) {
        if(!deleteByRoleId(roleId)){
            return false;
        }
        if(roleFuncs==null||roleFuncs.size()<=0){
            return true;
        }
        roleFuncs.forEach(roleFunc -> {
            roleFunc.setId(IdUtil.randomUUID());
            roleFunc.setRoleId(roleId);
        });

        return insertListEx(roleFuncs)==roleFuncs.size();
    }

    @Override
    public boolean deleteByRoleId(String roleId) {
        return roleFuncDao.deleteByRoleId(roleId);
    }

    @Override
    public List<RoleFunc> queryListBySearch(RoleFuncSearch search) {
        return roleFuncDao.queryListBySearch(search);
    }
}
