package com.tennetcn.free.authority.data.apimodel.paramdefine;

import com.tennetcn.free.authority.data.model.ParamDefine;
import com.tennetcn.free.core.message.web.BasePagerResp;
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
