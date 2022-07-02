package com.cditer.free.behavior.mapper;

import com.cditer.free.behavior.entity.model.WebVisitLog;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogSearch;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogView;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-14 12:17:56
 * @comment     操作日志表
 */

@Mapper
public interface IWebVisitLogMapper extends IMapper<WebVisitLog> {
    int queryCountBySearch(@Param("search") WebVisitLogSearch search);

    List<WebVisitLogView> queryListViewBySearch(@Param("search") WebVisitLogSearch search, @Param("pager") PagerModel pagerModel);

    WebVisitLogView queryModelViewBySearch(@Param("search") WebVisitLogSearch search);

    int queryVisitCountBySearch(@Param("search") WebVisitLogSearch search);
}
