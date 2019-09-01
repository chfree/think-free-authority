package com.tennetcn.free.develop.apimodel.dicttable;

import com.tennetcn.free.develop.model.DictTable;
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
public class DictTableListResp extends BasePagerResp {
    private List<DictTable> dictTables;
}
