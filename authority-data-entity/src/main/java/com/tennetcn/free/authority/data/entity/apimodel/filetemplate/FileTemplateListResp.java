package com.tennetcn.free.authority.data.entity.apimodel.filetemplate;

import com.tennetcn.free.authority.data.entity.model.FileTemplate;
import com.tennetcn.free.core.message.web.BasePagerResp;
import lombok.Data;
import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-15 08:45:28
 * @comment     文件模板表
 */

@Data
public class FileTemplateListResp extends BasePagerResp {
    private List<FileTemplate> fileTemplates;
}