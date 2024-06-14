package com.cditer.free.param.logical.mapper;


import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.param.data.entity.model.ColumnSetting;
import com.cditer.free.param.data.entity.viewmodel.ColumnSettingSearch;
import com.cditer.free.param.data.entity.viewmodel.ColumnSettingView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-06-14 12:41:59
 * @comment     列信息配置
 */

@Mapper
public interface IColumnSettingMapper extends IMapper<ColumnSetting>{

    int queryCountBySearch(@Param("search") ColumnSettingSearch search);

    List<ColumnSettingView> queryListViewBySearch(@Param("search") ColumnSettingSearch search, @Param("pager") PagerModel pagerModel);

    ColumnSettingView queryModelViewBySearch(@Param("search") ColumnSettingSearch search);

}