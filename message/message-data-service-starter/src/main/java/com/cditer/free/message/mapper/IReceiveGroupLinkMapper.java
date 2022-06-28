package com.cditer.free.message.mapper;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.message.entity.model.ReceiveGroupLink;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupLinkSearch;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupLinkView;
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
public interface IReceiveGroupLinkMapper extends IMapper<ReceiveGroupLink>{

    int queryCountBySearch(@Param("search") ReceiveGroupLinkSearch search);

    List<ReceiveGroupLinkView> queryListViewBySearch(@Param("search") ReceiveGroupLinkSearch search, @Param("pager") PagerModel pagerModel);

    ReceiveGroupLinkView queryModelViewBySearch(@Param("search") ReceiveGroupLinkSearch search);

}
