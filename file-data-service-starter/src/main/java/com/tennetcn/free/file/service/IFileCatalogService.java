package com.tennetcn.free.file.service;

import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.file.data.entity.model.FileCatalog;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-06 22:17:14
 * @comment     文件目录表
 */

public interface IFileCatalogService extends ISuperService<FileCatalog>{
    int queryCountBySearch(FileCatalogSearch search);

    List<FileCatalog> queryListBySearch(FileCatalogSearch search, PagerModel pagerModel);
}