package com.tennetcn.free.file.service;

import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.file.data.entity.model.FileChunk;
import com.tennetcn.free.file.data.entity.viewmodel.FileChunkSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-11-22 19:30:21
 * @comment     文件块
 */

public interface IFileChunkService extends ISuperService<FileChunk>{
    int queryCountBySearch(FileChunkSearch search);

    List<FileChunk> queryListBySearch(FileChunkSearch search, PagerModel pagerModel);

    List<Integer> queryListUploadChunk(FileChunkSearch search);

    boolean updateStatusByIdentifier(String status,String identifier);
}