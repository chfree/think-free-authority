package com.tennetcn.free.file.service;

import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.file.data.entity.model.FileInfo;
import com.tennetcn.free.file.data.entity.viewmodel.FileInfoSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:21:28
 * @comment     文件信息表
 */

public interface IFileInfoService extends ISuperService<FileInfo>{
    int queryCountBySearch(FileInfoSearch search);

    List<FileInfo> queryListBySearch(FileInfoSearch search, PagerModel pagerModel);

    FileInfo getFileInfoBySha1(String sha1);

    boolean deleteFileToDisk(FileInfo fileInfo);

    boolean deleteFilesToDisk(List<FileInfo> fileInfos);
}