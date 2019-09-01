package com.tennetcn.free.develop.apimodel.dictcolumn;

import com.tennetcn.free.develop.model.DictColumn;
import com.tennetcn.free.develop.model.DictTable;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-01 14:53
 * @comment
 */
@Data
public class SaveDictColumnReq {
    @NotBlank(message = "表信息不能为空")
    private String tableId;

    private List<DictColumn> dictColumns;
}
