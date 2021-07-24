package com.cditer.free.develop.data.entity.apimodel.dictcolumn;

import com.cditer.free.develop.data.entity.model.DictColumn;
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
