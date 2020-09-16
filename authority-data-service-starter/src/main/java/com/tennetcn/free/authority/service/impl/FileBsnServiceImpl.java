package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IFileBsnDao;
import com.tennetcn.free.authority.data.entity.model.FileBsn;
import com.tennetcn.free.authority.data.entity.viewmodel.FileBsnSearch;
import com.tennetcn.free.authority.data.entity.viewmodel.FileBsnView;
import com.tennetcn.free.authority.service.IFileBsnService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:27:13
 * @comment     文件业务表
 */

@Component
public class FileBsnServiceImpl extends SuperService<FileBsn> implements IFileBsnService {
    @Autowired
    IFileBsnDao fileBsnDao;

    @Override
    public int queryCountBySearch(FileBsnSearch search) {
        return fileBsnDao.queryCountBySearch(search);
    }

    @Override
    public int queryViewCountBySearch(FileBsnSearch search) {
        return fileBsnDao.queryViewCountBySearch(search);
    }

    @Override
    public List<FileBsn> queryListBySearch(FileBsnSearch search, PagerModel pagerModel) {
        return fileBsnDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public List<FileBsnView> queryViewListBySearch(FileBsnSearch search, PagerModel pagerModel) {
        return fileBsnDao.queryViewListBySearch(search,pagerModel);
    }

}