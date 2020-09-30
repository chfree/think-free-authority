package com.tennetcn.free.file.dao;

import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.file.data.entity.model.FileBsn;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnSearch;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnView;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:25:20
 * @comment     文件业务表
 */

public interface IFileBsnDao extends ISuperDao<FileBsn>{
    int queryCountBySearch(FileBsnSearch search);

    int queryViewCountBySearch(FileBsnSearch search);

    List<FileBsn> queryListBySearch(FileBsnSearch search, PagerModel pagerModel);

    FileBsn queryModelBySearch(FileBsnSearch search);

    List<FileBsnView> queryViewListBySearch(FileBsnSearch search, PagerModel pagerModel);

    FileBsnView queryViewModelBySearch(FileBsnSearch search);

    boolean deleteModel(String bsnId, String fileId);

    List<String> queryOneLinkFileId(List<String> fileIds);
}