package com.cditer.free.message.service;

import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.message.entity.model.MessageReceive;
import com.cditer.free.message.entity.viewmodel.MessageReceiveSearch;
import com.cditer.free.message.entity.viewmodel.MessageReceiveView;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 17:06:31
 * @comment     消息接收人
 */

public interface IMessageReceiveService extends ISuperService<MessageReceive>{

    /**
     * 根据搜索条件查询消息接收人条数
     * @param search 搜索条件
     * @return 消息接收人条数
     */
    int queryCountBySearch(MessageReceiveSearch search);

    /**
     * 根据搜索条件分页查询消息接收人对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 消息接收人对象集合
     */
    List<MessageReceiveView> queryListViewBySearch(MessageReceiveSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询消息接收人对象
     * @param search 搜索条件
     * @return 消息接收人对象
     */
    MessageReceiveView queryModelViewBySearch(MessageReceiveSearch search);

    /**
     * 根据id查询消息接收人对象
     * @param id 主键id
     * @return 消息接收人对象
     */
    MessageReceiveView queryModelViewById(String id);

    /**
     * 保存一个消息接收人对象
     * @param messageReceive 消息接收人对象
     * @return 是否成功
     */
    boolean saveMessageReceive(MessageReceive messageReceive);

}
