package com.tennetcn.free.develop.dao;

import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.develop.viewmodel.CodeTmpSearch;
import com.tennetcn.free.develop.model.CodeTmp;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:05:37
 * @comment     代码模板
 */

public interface ICodeTmpDao extends ISuperDao<CodeTmp>{
    int queryCountBySearch(CodeTmpSearch search);

    int queryCountByCheck(CodeTmpSearch search);

    List<CodeTmp> queryListBySearch(CodeTmpSearch search, PagerModel pagerModel);
}