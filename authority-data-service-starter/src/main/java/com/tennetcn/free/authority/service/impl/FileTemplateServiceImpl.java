package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IFileTemplateDao;
import com.tennetcn.free.authority.data.entity.model.FileTemplate;
import com.tennetcn.free.authority.data.entity.viewmodel.FileTemplateSearch;
import com.tennetcn.free.authority.service.IFileTemplateService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.core.message.data.PagerModel;
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

    @Override
    public int queryCountBySearch(FileTemplateSearch search) {
        return fileTemplateDao.queryCountBySearch(search);
    }

    @Override
    public List<FileTemplate> queryListBySearch(FileTemplateSearch search, PagerModel pagerModel) {
        return fileTemplateDao.queryListBySearch(search,pagerModel);
    }

}