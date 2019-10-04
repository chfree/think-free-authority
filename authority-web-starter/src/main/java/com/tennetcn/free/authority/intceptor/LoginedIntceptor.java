package com.tennetcn.free.authority.intceptor;

import com.tennetcn.free.authority.handle.ILoginedInceptor;
import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.authority.model.Department;
import com.tennetcn.free.authority.model.Role;
import com.tennetcn.free.authority.service.IButtonService;
import com.tennetcn.free.authority.service.IDepartmentService;
import com.tennetcn.free.authority.service.IMenuService;
import com.tennetcn.free.authority.service.IRoleService;
import com.tennetcn.free.authority.viewmodel.MenuRoute;
import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.web.webapi.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-10-04 11:59
 * @comment
 */
@Component
public class LoginedIntceptor implements ILoginedInceptor {
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IButtonService buttonService;

    @Autowired
    private IDepartmentService departmentService;
    @Override
    public void additionLoginModel(LoginModel loginModel, BaseResponse response) {
        List<Role> roles = roleService.queryListRoleByUserId(loginModel.getId());
        loginModel.put("roles", roles);

        Department department = departmentService.queryModel(loginModel.getString("departmentId"));
        if(department!=null){
            loginModel.put("departmentName",department.getFullName());
            loginModel.put("department",department);
        }

        if(roles!=null&&roles.size()>0){
            List<String> roleIds = roles.stream().map(role-> role.getId()).collect(Collectors.toList());

            List<MenuRoute> menuRouteList = menuService.queryMenuRouteFormatByRoleIds(roleIds);
            response.put("menuRoutes", menuRouteList);

            List<Button> buttons = buttonService.queryListByRoleIds(roleIds);
            response.put("buttons", buttons);
        }
    }
}
