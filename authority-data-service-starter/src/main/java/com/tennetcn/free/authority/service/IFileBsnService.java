package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.data.entity.model.FileBsn;
import com.tennetcn.free.authority.data.entity.viewmodel.FileBsnSearch;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:26:44
 * @comment     文件业务表
 */

public interface IFileBsnService extends ISuperService<FileBsn>{
    int queryCountBySearch(FileBsnSearch search);

    List<FileBsn> queryListBySearch(FileBsnSearch search, PagerModel pagerModel);
}