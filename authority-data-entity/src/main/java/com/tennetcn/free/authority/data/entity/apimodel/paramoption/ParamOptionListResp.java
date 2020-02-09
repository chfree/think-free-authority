package com.tennetcn.free.authority.data.entity.apimodel.paramoption;

import com.tennetcn.free.authority.data.entity.model.ParamOption;
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
public class ParamOptionListResp extends BasePagerResp {
    private List<ParamOption> paramOptions;
}