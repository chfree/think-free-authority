package com.tennetcn.free.file.service.impl;

import com.tennetcn.free.file.data.entity.model.FileCatalog;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogSearch;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.file.service.IFileCatalogService;
import com.tennetcn.free.file.dao.IFileCatalogDao;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-06 22:17:50
 * @comment     文件目录表
 */

@Component
public class FileCatalogServiceImpl extends SuperService<FileCatalog> implements IFileCatalogService{
    @Autowired
    IFileCatalogDao fileCatalogDao;

    @Override
    public int queryCountBySearch(FileCatalogSearch search) {
        return fileCatalogDao.queryCountBySearch(search);
    }

    @Override
    public List<FileCatalog> queryListBySearch(FileCatalogSearch search, PagerModel pagerModel) {
        return fileCatalogDao.queryListBySearch(search,pagerModel);
    }

}