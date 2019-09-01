package com.tennetcn.free.develop.apimodel.dictcolumn;

import com.tennetcn.free.develop.model.DictColumn;
import com.tennetcn.free.web.message.BasePagerResp;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-01 13:57
 * @comment
 */

@Data
public class DictColumnListResp extends BasePagerResp {
    private List<DictColumn> dictColumns;
}
