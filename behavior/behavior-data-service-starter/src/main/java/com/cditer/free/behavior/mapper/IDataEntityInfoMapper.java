package com.cditer.free.behavior.mapper;


import com.cditer.free.behavior.entity.model.DataEntityInfo;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEntityInfoView;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 21:56:30
 * @comment     数据实体信息
 */

@Mapper
public interface IDataEntityInfoMapper extends IMapper<DataEntityInfo>{

    int queryCountBySearch(@Param("search") DataEntityInfoSearch search);

    List<DataEntityInfoView> queryListViewBySearch(@Param("search") DataEntityInfoSearch search, @Param("pager") PagerModel pagerModel);

    DataEntityInfoView queryModelViewBySearch(@Param("search") DataEntityInfoSearch search);

}
