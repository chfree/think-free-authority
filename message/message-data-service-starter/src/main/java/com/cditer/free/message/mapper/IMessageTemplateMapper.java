package com.cditer.free.message.mapper;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.message.entity.model.MessageTemplate;
import com.cditer.free.message.entity.viewmodel.MessageTemplateSearch;
import com.cditer.free.message.entity.viewmodel.MessageTemplateView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-24 12:47:52
 * @comment     消息模板表
 */

@Mapper
public interface IMessageTemplateMapper extends IMapper<MessageTemplate> {
    int queryCountBySearch(@Param("search") MessageTemplateSearch search);

    List<MessageTemplateView> queryListViewBySearch(@Param("search") MessageTemplateSearch search, @Param("pager") PagerModel pagerModel);

    MessageTemplateView queryModelViewBySearch(@Param("search") MessageTemplateSearch search);
}