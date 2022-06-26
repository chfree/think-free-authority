package com.cditer.free.message.service;

import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.message.entity.model.MessageRead;
import com.cditer.free.message.entity.viewmodel.MessageReadSearch;
import com.cditer.free.message.entity.viewmodel.MessageReadView;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 17:12:38
 * @comment     消息读记录
 */

public interface IMessageReadService extends ISuperService<MessageRead>{

    /**
     * 根据搜索条件查询消息读记录条数
     * @param search 搜索条件
     * @return 消息读记录条数
     */
    int queryCountBySearch(MessageReadSearch search);

    /**
     * 根据搜索条件分页查询消息读记录对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 消息读记录对象集合
     */
    List<MessageReadView> queryListViewBySearch(MessageReadSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询消息读记录对象
     * @param search 搜索条件
     * @return 消息读记录对象
     */
    MessageReadView queryModelViewBySearch(MessageReadSearch search);

    /**
     * 根据id查询消息读记录对象
     * @param id 主键id
     * @return 消息读记录对象
     */
    MessageReadView queryModelViewById(String id);

    /**
     * 保存一个消息读记录对象
     * @param messageRead 消息读记录对象
     * @return 是否成功
     */
    boolean saveMessageRead(MessageRead messageRead);

}
