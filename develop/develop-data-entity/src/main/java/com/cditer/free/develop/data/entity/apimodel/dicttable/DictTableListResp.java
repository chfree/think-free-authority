package com.cditer.free.develop.data.entity.apimodel.dicttable;

import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.develop.data.entity.model.DictTable;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-01 13:57
 * @comment
 */

@Data
public class DictTableListResp extends BasePagerResp {
    private List<DictTable> dictTables;
}
