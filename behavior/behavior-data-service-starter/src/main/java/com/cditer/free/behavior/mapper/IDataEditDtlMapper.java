package com.cditer.free.behavior.mapper;


import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlView;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022-07-02 21:51:26
 * @comment 数据修改详情
 */

@Mapper
public interface IDataEditDtlMapper extends IMapper<DataEditDtl> {

    int queryCountBySearch(@Param("search") DataEditDtlSearch search);

    List<DataEditDtlView> queryListViewBySearch(@Param("search") DataEditDtlSearch search, @Param("pager") PagerModel pagerModel);

    DataEditDtlView queryModelViewBySearch(@Param("search") DataEditDtlSearch search);

}
