package com.tennetcn.free.authority.data.entity.apimodel.paramoption;

import com.tennetcn.free.authority.data.entity.model.ParamOption;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 22:37
 * @comment
 */

@Data
public class SaveParamOptionReq extends ParamOption {

    @NotBlank(message = "文本不能为空")
    private String text;

    @NotBlank(message = "值不能为空")
    private String value;

    @NotBlank(message = "参数定义不能为空")
    private String defineId;

    @NotBlank(message = "参数状态不能为空")
    private String status;
}
