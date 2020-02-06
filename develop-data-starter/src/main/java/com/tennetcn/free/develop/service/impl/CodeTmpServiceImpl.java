package com.tennetcn.free.develop.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.develop.model.CodeTmp;
import com.tennetcn.free.develop.service.ICodeTmpService;
import com.tennetcn.free.develop.dao.ICodeTmpDao;
import com.tennetcn.free.develop.viewmodel.CodeTmpSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:08:03
 * @comment     代码模板
 */

@Component
public class CodeTmpServiceImpl extends SuperService<CodeTmp> implements ICodeTmpService{
    @Autowired
    ICodeTmpDao codeTmpDao;

    @Override
    public int queryCountBySearch(CodeTmpSearch search) {
        return codeTmpDao.queryCountBySearch(search);
    }

    @Override
    public List<CodeTmp> queryListBySearch(CodeTmpSearch search, PagerModel pagerModel) {
        return codeTmpDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public boolean updatePub(String id, String pub) {
        CodeTmp codeTmp = queryModel(id);
        codeTmp.setPub(pub);

        return updateModel(codeTmp);
    }

}