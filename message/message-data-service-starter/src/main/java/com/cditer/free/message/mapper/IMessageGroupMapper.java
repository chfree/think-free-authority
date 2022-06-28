package com.cditer.free.message.mapper;


import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.message.entity.model.MessageGroup;
import com.cditer.free.message.entity.viewmodel.MessageGroupSearch;
import com.cditer.free.message.entity.viewmodel.MessageGroupView;
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
public interface IMessageGroupMapper extends IMapper<MessageGroup>{

    int queryCountBySearch(@Param("search") MessageGroupSearch search);

    List<MessageGroupView> queryListViewBySearch(@Param("search") MessageGroupSearch search, @Param("pager") PagerModel pagerModel);

    MessageGroupView queryModelViewBySearch(@Param("search") MessageGroupSearch search);

}
