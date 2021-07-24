package com.cditer.free.file.dao;

import com.cditer.free.file.data.entity.viewmodel.FileInfoSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;
import com.cditer.free.file.data.entity.model.FileInfo;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:20:06
 * @comment     文件信息表
 */

public interface IFileInfoDao extends ISuperDao<FileInfo>{
    int queryCountBySearch(FileInfoSearch search);

    List<FileInfo> queryListBySearch(FileInfoSearch search, PagerModel pagerModel);

    FileInfo queryModelBySearch(FileInfoSearch search);
}