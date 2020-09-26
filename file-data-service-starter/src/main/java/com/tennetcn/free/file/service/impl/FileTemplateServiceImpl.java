package com.tennetcn.free.file.service.impl;

import com.tennetcn.free.authority.dao.IFileTemplateDao;
import com.tennetcn.free.authority.data.entity.model.FileTemplate;
import com.tennetcn.free.authority.data.entity.viewmodel.FileTemplateSearch;
import com.tennetcn.free.authority.service.IFileBsnService;
import com.tennetcn.free.authority.service.IFileTemplateService;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-14 23:34:32
 * @comment     文件模板表
 */

@Component
public class FileTemplateServiceImpl extends SuperService<FileTemplate> implements IFileTemplateService {
    @Autowired
    IFileTemplateDao fileTemplateDao;

    @Autowired
    IFileBsnService fileBsnService;

    @Override
    public int queryCountBySearch(FileTemplateSearch search) {
        return fileTemplateDao.queryCountBySearch(search);
    }

    @Override
    public List<FileTemplate> queryListBySearch(FileTemplateSearch search, PagerModel pagerModel) {
        return fileTemplateDao.queryListBySearch(search,pagerModel);
    }

    @Override
    @Transactional
    public boolean deleteFileTemplate(String id) {
        fileBsnService.deleteByBsnId(id);

        return deleteModel(id);
    }

}