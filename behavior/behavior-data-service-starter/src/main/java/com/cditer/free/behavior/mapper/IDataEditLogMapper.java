package com.cditer.free.behavior.mapper;


import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogView;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022-07-02 21:46:38
 * @comment 数据修改记录
 */

@Mapper
public interface IDataEditLogMapper extends IMapper<DataEditLog> {

    int queryCountBySearch(@Param("search") DataEditLogSearch search);

    List<DataEditLogView> queryListViewBySearch(@Param("search") DataEditLogSearch search, @Param("pager") PagerModel pagerModel);

    DataEditLogView queryModelViewBySearch(@Param("search") DataEditLogSearch search);

}