package com.cditer.free.message.service;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.message.entity.model.MessageGroup;
import com.cditer.free.message.entity.viewmodel.MessageGroupSearch;
import com.cditer.free.message.entity.viewmodel.MessageGroupView;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:33:43
 * @comment     消息组
 */

public interface IMessageGroupService extends ISuperService<MessageGroup>{

    /**
     * 根据搜索条件查询消息组条数
     * @param search 搜索条件
     * @return 消息组条数
     */
    int queryCountBySearch(MessageGroupSearch search);

    /**
     * 根据搜索条件分页查询消息组对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 消息组对象集合
     */
    List<MessageGroupView> queryListViewBySearch(MessageGroupSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询消息组对象
     * @param search 搜索条件
     * @return 消息组对象
     */
    MessageGroupView queryModelViewBySearch(MessageGroupSearch search);

    /**
     * 根据id查询消息组对象
     * @param id 主键id
     * @return 消息组对象
     */
    MessageGroupView queryModelViewById(String id);

    /**
     * 保存一个消息组对象
     * @param messageGroup 消息组对象
     * @return 是否成功
     */
    boolean saveMessageGroup(MessageGroup messageGroup);

}