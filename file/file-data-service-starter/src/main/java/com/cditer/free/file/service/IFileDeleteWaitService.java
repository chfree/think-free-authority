package com.cditer.free.file.service;

import com.cditer.free.file.data.entity.viewmodel.FileDeleteWaitSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.file.data.entity.model.FileDeleteWait;
import com.cditer.free.file.data.entity.model.FileInfo;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-21 12:05:21
 * @comment     文件删除等待表
 */

public interface IFileDeleteWaitService extends ISuperService<FileDeleteWait>{
    int queryCountBySearch(FileDeleteWaitSearch search);

    List<FileDeleteWait> queryListBySearch(FileDeleteWaitSearch search, PagerModel pagerModel);

    boolean moveFileToDelayDir(FileInfo fileInfo);

    boolean moveFilesToDelayDir(List<FileInfo> fileInfos);

    /**
     * 获取能删除的文件
     */
    List<FileDeleteWait> queryCanDeleteFile(PagerModel pagerModel);
}