package com.tennetcn.free.authority.viewmodel;

import com.tennetcn.free.authority.model.Menu;
import com.tennetcn.free.authority.model.MenuButton;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-20 22:40
 * @comment
 */

@Data
public class MenuButtonTree extends Menu {
    private List<MenuButtonTree> children;

    private List<MenuButton> menuButtons;

    private String parentName;
}