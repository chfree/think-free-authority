package com.cditer.free.file.service.impl;

import com.cditer.free.file.data.entity.viewmodel.FileTemplateSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.file.dao.IFileTemplateDao;
import com.cditer.free.file.data.entity.model.FileTemplate;
import com.cditer.free.file.service.IFileBsnService;
import com.cditer.free.file.service.IFileTemplateService;
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