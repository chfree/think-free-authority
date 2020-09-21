package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.data.entity.model.FileDeleteWait;
import com.tennetcn.free.authority.data.entity.viewmodel.FileDeleteWaitSearch;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.core.message.data.PagerModel;
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
}