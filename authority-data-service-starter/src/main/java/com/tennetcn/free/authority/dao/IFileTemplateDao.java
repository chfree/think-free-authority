package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.data.entity.model.FileTemplate;
import com.tennetcn.free.authority.data.entity.viewmodel.FileTemplateSearch;
import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-14 23:30:19
 * @comment     文件模板表
 */

public interface IFileTemplateDao extends ISuperDao<FileTemplate>{
    int queryCountBySearch(FileTemplateSearch search);

    List<FileTemplate> queryListBySearch(FileTemplateSearch search, PagerModel pagerModel);
}