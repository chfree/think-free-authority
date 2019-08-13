package com.tennetcn.free.authority.apimodel.menu;

import com.tennetcn.free.authority.model.Menu;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 22:57
 * @comment
 */

@Data
public class SaveMenuReq extends Menu {

    @NotBlank(message = "名称不能为空")
    private String name;
}
