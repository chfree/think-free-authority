package com.tennetcn.free.file.dao;

import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.file.data.entity.model.FileCatalog;
import com.tennetcn.free.file.data.entity.model.FileInfo;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogSearch;
import com.tennetcn.free.file.data.entity.viewmodel.FileInfoView;

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