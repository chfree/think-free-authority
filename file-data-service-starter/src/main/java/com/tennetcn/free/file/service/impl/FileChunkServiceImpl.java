package com.tennetcn.free.file.service.impl;

import com.tennetcn.free.file.dao.IFileChunkDao;
import com.tennetcn.free.file.data.entity.model.FileChunk;
import com.tennetcn.free.file.data.entity.viewmodel.FileChunkSearch;
import com.tennetcn.free.file.service.IFileChunkService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-11-22 19:31:41
 * @comment     文件块
 */

@Component
public class FileChunkServiceImpl extends SuperService<FileChunk> implements IFileChunkService {
    @Autowired
    IFileChunkDao fileChunkDao;

    @Override
    public int queryCountBySearch(FileChunkSearch search) {
        return fileChunkDao.queryCountBySearch(search);
    }

    @Override
    public List<FileChunk> queryListBySearch(FileChunkSearch search, PagerModel pagerModel) {
        return fileChunkDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public List<Integer> queryListUploadChunk(FileChunkSearch search) {
        return fileChunkDao.queryListUploadChunk(search);
    }

    @Override
    public boolean updateStatusByIdentifier(String status, String identifier) {
        return fileChunkDao.updateStatusByIdentifier(status, identifier);
    }

}