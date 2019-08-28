package com.tennetcn.free.authority.apimodel.paramoption;

import com.tennetcn.free.authority.message.PagerResp;
import com.tennetcn.free.authority.model.ParamDefine;
import com.tennetcn.free.authority.model.ParamOption;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class ParamOptionListResp extends PagerResp {
    private List<ParamOption> paramOptions;
}
