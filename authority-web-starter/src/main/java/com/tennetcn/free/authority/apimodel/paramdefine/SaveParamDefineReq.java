package com.tennetcn.free.authority.apimodel.paramdefine;

import com.tennetcn.free.authority.model.ParamDefine;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 22:37
 * @comment
 */

@Data
public class SaveParamDefineReq extends ParamDefine {

    @NotBlank(message = "名称不能为空")
    private String name;
}
