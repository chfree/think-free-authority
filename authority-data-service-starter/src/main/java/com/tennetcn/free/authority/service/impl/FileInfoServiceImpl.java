package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IFileInfoDao;
import com.tennetcn.free.authority.data.entity.model.FileInfo;
import com.tennetcn.free.authority.data.entity.viewmodel.FileInfoSearch;
import com.tennetcn.free.authority.service.IFileInfoService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:22:03
 * @comment     文件信息表
 */

@Component
public class FileInfoServiceImpl extends SuperService<FileInfo> implements IFileInfoService {
    @Autowired
    IFileInfoDao fileInfoDao;

    @Override
    public int queryCountBySearch(FileInfoSearch search) {
        return fileInfoDao.queryCountBySearch(search);
    }

    @Override
    public List<FileInfo> queryListBySearch(FileInfoSearch search, PagerModel pagerModel) {
        return fileInfoDao.queryListBySearch(search,pagerModel);
    }

}