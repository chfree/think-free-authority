package com.cditer.free.file.data.entity.apimodel.filetemplate;

import com.cditer.free.file.data.entity.viewmodel.FileTemplateSearch;
import com.cditer.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-15 08:45:06
 * @comment     文件模板表
 */

@Data
public class FileTemplateListReq extends BasePagerReq {
    private FileTemplateSearch search;
}