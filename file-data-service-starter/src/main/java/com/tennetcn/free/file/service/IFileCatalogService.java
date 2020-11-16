package com.tennetcn.free.file.service;

import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.file.data.entity.model.FileCatalog;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogSearch;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogTree;

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

    List<FileCatalog> queryListByTopLevel(FileCatalogSearch search);

    List<FileCatalog> queryListByTwoLevel(List<String> topIds,FileCatalogSearch search);

    List<FileCatalogTree> queryListByOwnShow(String userId);

    List<FileCatalog> queryPathList(String id);

    List<FileCatalog> queryChildList(String uesrId,String id);

    boolean saveNewFolder(String userId,String parentId,String folderName);

    boolean renameFolder(String id,String folderName);
}