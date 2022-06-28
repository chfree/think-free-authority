package com.cditer.free.message.service;

import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.message.entity.model.MessageGroupLink;
import com.cditer.free.message.entity.viewmodel.MessageGroupLinkSearch;
import com.cditer.free.message.entity.viewmodel.MessageGroupLinkView;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:48:05
 * @comment     消息组连接
 */

public interface IMessageGroupLinkService extends ISuperService<MessageGroupLink>{

    /**
     * 根据搜索条件查询消息组连接条数
     * @param search 搜索条件
     * @return 消息组连接条数
     */
    int queryCountBySearch(MessageGroupLinkSearch search);

    /**
     * 根据搜索条件分页查询消息组连接对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 消息组连接对象集合
     */
    List<MessageGroupLinkView> queryListViewBySearch(MessageGroupLinkSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询消息组连接对象
     * @param search 搜索条件
     * @return 消息组连接对象
     */
    MessageGroupLinkView queryModelViewBySearch(MessageGroupLinkSearch search);

    /**
     * 根据id查询消息组连接对象
     * @param id 主键id
     * @return 消息组连接对象
     */
    MessageGroupLinkView queryModelViewById(String id);

    /**
     * 保存一个消息组连接对象
     * @param messageGroupLink 消息组连接对象
     * @return 是否成功
     */
    boolean saveMessageGroupLink(MessageGroupLink messageGroupLink);

}