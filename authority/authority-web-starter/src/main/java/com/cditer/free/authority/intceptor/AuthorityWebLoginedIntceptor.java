package com.cditer.free.authority.intceptor;

import com.cditer.free.authority.data.entity.model.Department;
import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.security.handle.ILoginedIntceptor;
import com.cditer.free.authority.logical.service.IDepartmentService;
import com.cditer.free.security.message.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-11-02 16:23
 * @comment
 */

@Component
public class AuthorityWebLoginedIntceptor implements ILoginedIntceptor {
    private final static int ORDER = 50;

    @Autowired
    IDepartmentService departmentService;

    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    public void logined(LoginModel loginModel) {
        User loginUser = (User)loginModel.get("user");
        Department department = departmentService.queryModel(loginUser.getDepartmentId());
        if(department!=null){
            loginModel.put("departmentName",department.getFullName());
            loginModel.put("department",department);
            loginModel.setCurrentDeptId(department.getId());
            loginModel.setCurrentDeptName(department.getShortName());
        }
    }
}
