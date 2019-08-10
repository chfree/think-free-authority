package com.tennetcn.free.authority.apimodel.button;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.data.enums.ModelStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 22:37
 * @comment
 */

@Data
public class SaveButtonReq extends Button {

    @NotBlank(message = "按钮名称不能为空")
    private String name;
}