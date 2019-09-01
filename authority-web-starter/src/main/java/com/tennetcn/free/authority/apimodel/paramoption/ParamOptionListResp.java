package com.tennetcn.free.authority.apimodel.paramoption;

import com.tennetcn.free.authority.model.ParamOption;
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
public class ParamOptionListResp extends BasePagerResp {
    private List<ParamOption> paramOptions;
}
