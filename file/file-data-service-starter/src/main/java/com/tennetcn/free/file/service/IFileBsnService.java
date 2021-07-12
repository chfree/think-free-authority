package com.tennetcn.free.file.service;

import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.file.data.entity.model.FileBsn;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnSearch;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnView;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:26:44
 * @comment     文件业务表
 */

public interface IFileBsnService extends ISuperService<FileBsn>{
    int queryCountBySearch(FileBsnSearch search);

    int queryViewCountBySearch(FileBsnSearch search);

    List<FileBsn> queryListBySearch(FileBsnSearch search, PagerModel pagerModel);

    FileBsn queryModelBySearch(FileBsnSearch search);

    List<FileBsnView> queryViewListBySearch(FileBsnSearch search, PagerModel pagerModel);

    FileBsnView queryViewModelBySearch(FileBsnSearch search);

    FileBsnView queryViewModelById(String id);

    boolean deleteModel(String bsnId,String fileId);

    List<String> queryOneLinkFileId(List<String> fileIds);

    /**
     * 根据业务id删除文件-延时
     * 一个业务id可能包含多个文件
     * 删除文件信息时，要判断该文件是否只有一个业务关联项
     */
    boolean deleteDelayByBsnId(String bsnId);

    /**
     * 根据业务id删除文件
     * 一个业务id可能包含多个文件
     * 删除文件信息时，要判断该文件是否只有一个业务关联项
     */
    boolean deleteByBsnId(String bsnId);

    /**
     * 保存文件业务信息，会进行名字重名的判断
     */
    boolean saveFileBsn(FileBsn fileBsn);

    /**
     * 根据业务单号查询下一个间隔的顺序号
     */
    Integer queryNextSeqIndex(String bsnId, String displayName);
}