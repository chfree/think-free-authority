package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IFileDeleteWaitDao;
import com.tennetcn.free.authority.data.entity.model.FileDeleteWait;
import com.tennetcn.free.authority.data.entity.viewmodel.FileDeleteWaitSearch;
import com.tennetcn.free.authority.service.IFileDeleteWaitService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-21 12:06:00
 * @comment     文件删除等待表
 */

@Component
public class FileDeleteWaitServiceImpl extends SuperService<FileDeleteWait> implements IFileDeleteWaitService {
    @Autowired
    IFileDeleteWaitDao fileDeleteWaitDao;

    @Override
    public int queryCountBySearch(FileDeleteWaitSearch search) {
        return fileDeleteWaitDao.queryCountBySearch(search);
    }

    @Override
    public List<FileDeleteWait> queryListBySearch(FileDeleteWaitSearch search, PagerModel pagerModel) {
        return fileDeleteWaitDao.queryListBySearch(search,pagerModel);
    }

}