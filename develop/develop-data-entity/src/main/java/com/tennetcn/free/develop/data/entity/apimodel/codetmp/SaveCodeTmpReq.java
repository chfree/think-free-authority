package com.tennetcn.free.develop.data.entity.apimodel.codetmp;

import com.tennetcn.free.develop.data.entity.model.CodeTmp;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:28:45
 * @comment     代码模板
 */

@Data
public class SaveCodeTmpReq extends CodeTmp {

    @NotBlank(message = "模板名称为必填项")
    private String name;
}