package com.cditer.free.message.mapper;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.message.entity.model.MessageInfo;
import com.cditer.free.message.entity.viewmodel.MessageInfoSearch;
import com.cditer.free.message.entity.viewmodel.MessageInfoView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 16:43:13
 * @comment     消息表
 */

@Mapper
public interface IMessageInfoMapper extends IMapper<MessageInfo>{

    int queryCountBySearch(@Param("search") MessageInfoSearch search);

    List<MessageInfoView> queryListViewBySearch(@Param("search") MessageInfoSearch search, @Param("pager") PagerModel pagerModel);

    MessageInfoView queryModelViewBySearch(@Param("search") MessageInfoSearch search);

    int queryMessageCount(@Param("search")MessageInfoSearch search);

    List<MessageInfoView> queryMessageList(@Param("search")MessageInfoSearch search,@Param("pager") PagerModel pagerModel);
}
