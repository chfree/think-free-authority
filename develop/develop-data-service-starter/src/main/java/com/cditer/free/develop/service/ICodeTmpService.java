package com.cditer.free.develop.service;

import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.develop.data.entity.viewmodel.CodeTmpSearch;
import com.cditer.free.develop.data.entity.model.CodeTmp;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:07:44
 * @comment     代码模板
 */

public interface ICodeTmpService extends ISuperService<CodeTmp>{
    int queryCountBySearch(CodeTmpSearch search);

    int queryCountByCheck(CodeTmpSearch search);

    List<CodeTmp> queryListBySearch(CodeTmpSearch search, PagerModel pagerModel);

    boolean updatePub(String id,String pub);
}