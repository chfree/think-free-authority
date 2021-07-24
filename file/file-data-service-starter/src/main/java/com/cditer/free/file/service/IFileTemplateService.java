package com.cditer.free.file.service;

import com.cditer.free.file.data.entity.viewmodel.FileTemplateSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.file.data.entity.model.FileTemplate;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-14 23:33:02
 * @comment     文件模板表
 */

public interface IFileTemplateService extends ISuperService<FileTemplate>{
    int queryCountBySearch(FileTemplateSearch search);

    List<FileTemplate> queryListBySearch(FileTemplateSearch search, PagerModel pagerModel);

    boolean deleteFileTemplate(String id);
}