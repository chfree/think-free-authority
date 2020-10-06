package com.tennetcn.free.file.dao;

import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.file.data.entity.model.FileCatalog;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-06 22:13:41
 * @comment     文件目录表
 */

public interface IFileCatalogDao extends ISuperDao<FileCatalog>{
    int queryCountBySearch(FileCatalogSearch search);

    List<FileCatalog> queryListBySearch(FileCatalogSearch search, PagerModel pagerModel);
}