package com.tennetcn.free.file.service.impl;

import com.tennetcn.free.authority.dao.IFileInfoDao;
import com.tennetcn.free.authority.data.entity.model.FileInfo;
import com.tennetcn.free.authority.data.entity.viewmodel.FileInfoSearch;
import com.tennetcn.free.authority.service.IFileInfoService;
import com.tennetcn.free.authority.utils.FilePathUtils;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
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

    @Override
    public FileInfo getFileInfoBySha1(String sha1) {
        FileInfoSearch fileInfoSearch = new FileInfoSearch();
        fileInfoSearch.setSha1(sha1);

        return fileInfoDao.queryModelBySearch(fileInfoSearch);
    }

    @Override
    public boolean deleteFileToDisk(FileInfo fileInfo) {
        String diskFilePathName = FilePathUtils.getDiskPath() + fileInfo.getPath() + fileInfo.getFileName();
        File diskFile = new File(diskFilePathName);

        if(diskFile.exists()&&diskFile.isFile()){
            return diskFile.delete();
        }
        return true;
    }

    @Override
    public boolean deleteFilesToDisk(List<FileInfo> fileInfos) {
        if(fileInfos==null||fileInfos.isEmpty()){
            return true;
        }
        for (FileInfo fileInfo : fileInfos) {
            deleteFileToDisk(fileInfo);
        }
        return true;
    }

}