package com.cditer.free.file.service;

import com.cditer.free.file.data.entity.viewmodel.FileInfoSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.file.data.entity.model.FileInfo;

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