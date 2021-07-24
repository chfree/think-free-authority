package com.cditer.free.file.dao;

import com.cditer.free.file.data.entity.viewmodel.FileCatalogSearch;
import com.cditer.free.file.data.entity.viewmodel.FileInfoView;
import com.cditer.free.data.dao.base.ISuperDao;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.file.data.entity.model.FileCatalog;

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

    List<FileCatalog> queryListByTopLevel(FileCatalogSearch search);

    List<FileCatalog> queryListByTwoLevel(List<String> topIds,FileCatalogSearch search);

    List<FileCatalog> queryPathList(String id);

    List<FileCatalog> queryChildList(String userId,String id);

    List<FileInfoView> queryFileInfoList(String catalogId);
}