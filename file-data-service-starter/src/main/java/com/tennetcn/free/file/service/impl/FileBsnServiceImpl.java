package com.tennetcn.free.file.service.impl;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.authority.service.IParamSettingService;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.file.dao.IFileBsnDao;
import com.tennetcn.free.file.data.entity.model.FileBsn;
import com.tennetcn.free.file.data.entity.model.FileDeleteWait;
import com.tennetcn.free.file.data.entity.model.FileInfo;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnSearch;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnView;
import com.tennetcn.free.file.data.enums.FileParamSettingKeys;
import com.tennetcn.free.file.service.IFileBsnService;
import com.tennetcn.free.file.service.IFileDeleteWaitService;
import com.tennetcn.free.file.service.IFileInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:27:13
 * @comment     文件业务表
 */

@Component
public class FileBsnServiceImpl extends SuperService<FileBsn> implements IFileBsnService {
    @Autowired
    IFileBsnDao fileBsnDao;

    @Autowired
    IFileInfoService fileInfoService;

    @Autowired
    IParamSettingService paramSettingService;

    @Autowired
    IFileDeleteWaitService fileDeleteWaitService;

    @Override
    public int queryCountBySearch(FileBsnSearch search) {
        return fileBsnDao.queryCountBySearch(search);
    }

    @Override
    public int queryViewCountBySearch(FileBsnSearch search) {
        return fileBsnDao.queryViewCountBySearch(search);
    }

    @Override
    public List<FileBsn> queryListBySearch(FileBsnSearch search, PagerModel pagerModel) {
        return fileBsnDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public FileBsn queryModelBySearch(FileBsnSearch search) {
        return fileBsnDao.queryModelBySearch(search);
    }

    @Override
    public List<FileBsnView> queryViewListBySearch(FileBsnSearch search, PagerModel pagerModel) {
        return fileBsnDao.queryViewListBySearch(search,pagerModel);
    }

    @Override
    public boolean deleteModel(String bsnId, String fileId) {
        return fileBsnDao.deleteModel(bsnId,fileId);
    }

    @Override
    public List<String> queryOneLinkFileId(List<String> fileIds) {
        return fileBsnDao.queryOneLinkFileId(fileIds);
    }


    /**
     * 一个业务id可能包含多个文件
     * 删除文件信息时，要判断该文件是否只有一个业务关联项
     */
    @Override
    @Transactional
    public boolean deleteByBsnId(String bsnId) {
        return doDeleteByBsnId(bsnId, false);
    }

    @Override
    @Transactional
    public boolean deleteDelayByBsnId(String bsnId) {
        return doDeleteByBsnId(bsnId, true);
    }

    private boolean doDeleteByBsnId(String bsnId,boolean delay){
        FileBsnSearch search = new FileBsnSearch();
        search.setBsnId(bsnId);

        List<FileBsn> fileBsns = queryListBySearch(search, new PagerModel(1000, 1));

        if(fileBsns==null||fileBsns.isEmpty()){
            return true;
        }

        List<String> fileIds = fileBsns.stream().map(item -> item.getFileId()).distinct().collect(Collectors.toList());
        // 找出只有一个业务关联项的文件id，去删除文件信息，及文件
        List<String> oneLinkFileIds = queryOneLinkFileId(fileIds);

        List<FileInfo> fileInfos = fileInfoService.queryListByIds(fileIds);

        List<FileInfo> oneLinkFileInfos = fileInfos.stream().filter(item -> oneLinkFileIds.contains(item.getFileId())).collect(Collectors.toList());


        if(delay) {
            // 保存到临时待删除表
            saveToDeleteWait(fileBsns,fileInfos,oneLinkFileInfos);
        }

        // 删除fileBsn
        List<String> fileBsnIds = fileBsns.stream().map(item -> item.getId()).collect(Collectors.toList());
        deleteByIds(fileBsnIds);

        // 删除文件
        fileInfoService.deleteFilesToDisk(oneLinkFileInfos);

        // 删除文件信息
        fileInfoService.deleteByIds(oneLinkFileIds);

        return true;
    }

    private boolean saveToDeleteWait(List<FileBsn> fileBsns,List<FileInfo> fileInfos,List<FileInfo> oneLinkFileInfos){
        int fileDeleteDelayDays = paramSettingService.queryIntValue(FileParamSettingKeys.FILE_DELETE_DELAY_DAYS, 3);

        List<FileDeleteWait> fileDeleteWaits = fileBsns.stream().map(item -> {
            FileDeleteWait fileDeleteWait = new FileDeleteWait();

            Optional<FileInfo> first = fileInfos.stream().filter(fileInfo -> {
                return fileInfo.getFileId().equals(item.getFileId());
            }).findFirst();
            if (first.isPresent()) {
                // 先复制fileInfo
                BeanUtils.copyProperties(first.get(), fileDeleteWait);
            }

            BeanUtils.copyProperties(item, fileDeleteWait);

            fileDeleteWait.setAddDate(DateUtil.date());
            fileDeleteWait.setWaitDay(fileDeleteDelayDays);

            return fileDeleteWait;
        }).collect(Collectors.toList());

        if(oneLinkFileInfos!=null&&oneLinkFileInfos.size()>0){ // 有要删除的就备份到delay目录
            fileDeleteWaitService.moveFilesToDelayDir(oneLinkFileInfos);
        }


        return fileDeleteWaitService.batchInsertList(fileDeleteWaits) == fileDeleteWaits.size();
    }

}