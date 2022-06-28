package com.cditer.free.message.mapper;


import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.message.entity.model.ReceiveGroup;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupSearch;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:31:46
 * @comment     消息组
 */

@Mapper
public interface IReceiveGroupMapper extends IMapper<ReceiveGroup>{

    int queryCountBySearch(@Param("search") ReceiveGroupSearch search);

    List<ReceiveGroupView> queryListViewBySearch(@Param("search") ReceiveGroupSearch search, @Param("pager") PagerModel pagerModel);

    ReceiveGroupView queryModelViewBySearch(@Param("search") ReceiveGroupSearch search);

}
