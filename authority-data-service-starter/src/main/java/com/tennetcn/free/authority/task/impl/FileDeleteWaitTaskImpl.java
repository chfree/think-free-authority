package com.tennetcn.free.authority.task.impl;

import com.tennetcn.free.authority.data.entity.model.FileDeleteWait;
import com.tennetcn.free.authority.service.IFileDeleteWaitService;
import com.tennetcn.free.authority.task.IFileDeleteWaitTask;
import com.tennetcn.free.authority.utils.FilePathUtils;
import com.tennetcn.free.core.message.data.PagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-24 09:42
 * @comment
 */

@Component
public class FileDeleteWaitTaskImpl implements IFileDeleteWaitTask {

    @Autowired
    IFileDeleteWaitService fileDeleteWaitService;

    @Override
    public void deleteWaitFile() {
        int pageSize = 100;
        PagerModel pagerModel = new PagerModel(pageSize,1);
        boolean hasData = false;

        do {
            List<FileDeleteWait> fileDeleteWaits = fileDeleteWaitService.queryCanDeleteFile(pagerModel);
            hasData = (fileDeleteWaits!=null&&fileDeleteWaits.size()==pageSize);

            // 执行删除文件和信息
            doDeleteWaitFile(fileDeleteWaits);
        }while (hasData);
    }

    private void doDeleteWaitFile(List<FileDeleteWait> fileDeleteWaits){
        if(fileDeleteWaits==null||fileDeleteWaits.isEmpty()){
            return;
        }

        for (FileDeleteWait fileDeleteWait : fileDeleteWaits) {
            String filePathName = FilePathUtils.getDiskPath() +FilePathUtils.delayPath+ fileDeleteWait.getPath() + fileDeleteWait.getFileName();

            File file = new File(filePathName);
            if(file.exists()&&file.isFile()){
                file.delete();
            }
        }

        List<String> ids = fileDeleteWaits.stream().map(item -> item.getId()).collect(Collectors.toList());

        fileDeleteWaitService.deleteByIds(ids);

    }
}
