package com.cditer.free.behavior.mapper;

import com.cditer.free.behavior.entity.model.DataPropertyMapping;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingSearch;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingView;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 21:55:11
 * @comment     属性映射表
 */

@Mapper
public interface IDataPropertyMappingMapper extends IMapper<DataPropertyMapping>{

    int queryCountBySearch(@Param("search") DataPropertyMappingSearch search);

    List<DataPropertyMappingView> queryListViewBySearch(@Param("search") DataPropertyMappingSearch search, @Param("pager") PagerModel pagerModel);

    DataPropertyMappingView queryModelViewBySearch(@Param("search") DataPropertyMappingSearch search);

}