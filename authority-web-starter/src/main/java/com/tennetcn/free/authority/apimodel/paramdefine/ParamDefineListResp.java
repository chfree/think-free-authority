package com.tennetcn.free.authority.apimodel.paramdefine;

import com.tennetcn.free.authority.model.ParamDefine;
import com.tennetcn.free.web.message.BasePagerResp;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class ParamDefineListResp extends BasePagerResp {
    private List<ParamDefine> paramDefines;
}
