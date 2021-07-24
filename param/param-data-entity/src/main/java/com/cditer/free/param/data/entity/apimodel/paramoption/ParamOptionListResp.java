package com.cditer.free.param.data.entity.apimodel.paramoption;

import com.cditer.free.param.data.entity.model.ParamOption;
import com.cditer.free.core.message.web.BasePagerResp;
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
