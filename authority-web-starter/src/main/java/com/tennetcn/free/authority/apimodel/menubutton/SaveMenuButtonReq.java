package com.tennetcn.free.authority.apimodel.menubutton;

import com.tennetcn.free.authority.model.MenuButton;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:00
 * @comment
 */

@Data
public class SaveMenuButtonReq {
    @NotBlank(message = "菜单id不能为空")
    private String menuId;

    private List<MenuButton> menuButtons;


}
