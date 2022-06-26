package com.cditer.free.message.mapper;


import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.message.entity.model.MessageRead;
import com.cditer.free.message.entity.viewmodel.MessageReadSearch;
import com.cditer.free.message.entity.viewmodel.MessageReadView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 17:10:28
 * @comment     消息读记录
 */

@Mapper
public interface IMessageReadMapper extends IMapper<MessageRead>{

    int queryCountBySearch(@Param("search") MessageReadSearch search);

    List<MessageReadView> queryListViewBySearch(@Param("search") MessageReadSearch search, @Param("pager") PagerModel pagerModel);

    MessageReadView queryModelViewBySearch(@Param("search") MessageReadSearch search);

}
