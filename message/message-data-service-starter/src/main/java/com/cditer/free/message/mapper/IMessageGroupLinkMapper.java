package com.cditer.free.message.mapper;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.message.entity.model.MessageGroupLink;
import com.cditer.free.message.entity.viewmodel.MessageGroupLinkSearch;
import com.cditer.free.message.entity.viewmodel.MessageGroupLinkView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:40:18
 * @comment     消息组连接
 */

@Mapper
public interface IMessageGroupLinkMapper extends IMapper<MessageGroupLink>{

    int queryCountBySearch(@Param("search") MessageGroupLinkSearch search);

    List<MessageGroupLinkView> queryListViewBySearch(@Param("search") MessageGroupLinkSearch search, @Param("pager") PagerModel pagerModel);

    MessageGroupLinkView queryModelViewBySearch(@Param("search") MessageGroupLinkSearch search);

}
