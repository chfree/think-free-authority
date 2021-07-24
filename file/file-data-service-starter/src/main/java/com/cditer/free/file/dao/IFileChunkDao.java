package com.cditer.free.file.dao;

import com.cditer.free.file.data.entity.viewmodel.FileChunkSearch;
import com.cditer.free.data.dao.base.ISuperDao;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.file.data.entity.model.FileChunk;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-11-22 19:27:07
 * @comment     文件块
 */

public interface IFileChunkDao extends ISuperDao<FileChunk>{
    int queryCountBySearch(FileChunkSearch search);

    List<FileChunk> queryListBySearch(FileChunkSearch search, PagerModel pagerModel);

    List<Integer> queryListUploadChunk(FileChunkSearch search);

    boolean updateStatusByIdentifier(String status,String identifier);
}