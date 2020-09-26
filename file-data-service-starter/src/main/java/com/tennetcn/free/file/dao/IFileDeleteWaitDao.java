package com.tennetcn.free.file.dao;

import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.file.data.entity.model.FileDeleteWait;
import com.tennetcn.free.file.data.entity.viewmodel.FileDeleteWaitSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-21 11:55:07
 * @comment     文件删除等待表
 */

public interface IFileDeleteWaitDao extends ISuperDao<FileDeleteWait>{
    int queryCountBySearch(FileDeleteWaitSearch search);

    List<FileDeleteWait> queryListBySearch(FileDeleteWaitSearch search, PagerModel pagerModel);

    /**
     * 查询符合删除的待删除信息
     */
    List<FileDeleteWait> queryCanDeleteFile(PagerModel pagerModel);
}