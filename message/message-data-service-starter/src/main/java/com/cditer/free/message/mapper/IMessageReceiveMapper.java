package com.cditer.free.message.mapper;


import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.message.entity.model.MessageReceive;
import com.cditer.free.message.entity.viewmodel.MessageReceiveSearch;
import com.cditer.free.message.entity.viewmodel.MessageReceiveView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 17:03:08
 * @comment     消息接收人
 */

@Mapper
public interface IMessageReceiveMapper extends IMapper<MessageReceive>{

    int queryCountBySearch(@Param("search") MessageReceiveSearch search);

    List<MessageReceiveView> queryListViewBySearch(@Param("search") MessageReceiveSearch search, @Param("pager") PagerModel pagerModel);

    MessageReceiveView queryModelViewBySearch(@Param("search") MessageReceiveSearch search);

}
