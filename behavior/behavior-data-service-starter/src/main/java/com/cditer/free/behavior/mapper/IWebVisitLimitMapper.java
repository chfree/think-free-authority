package com.cditer.free.behavior.mapper;

import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-15 12:53:04
 * @comment     web访问控制表
 */

@Mapper
public interface IWebVisitLimitMapper extends IMapper<WebVisitLimit> {
    WebVisitLimit queryModelBySearch(@Param("search") WebVisitLimitSearch search);

    int queryCountBySearch(@Param("search")WebVisitLimitSearch search);

    List<WebVisitLimit> queryListBySearch(@Param("search")WebVisitLimitSearch search, @Param("pager") PagerModel pagerModel);
}
