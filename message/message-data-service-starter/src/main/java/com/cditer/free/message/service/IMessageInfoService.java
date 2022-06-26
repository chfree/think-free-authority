package com.cditer.free.message.service;

import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.message.entity.model.MessageInfo;
import com.cditer.free.message.entity.viewmodel.MessageInfoSearch;
import com.cditer.free.message.entity.viewmodel.MessageInfoView;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 16:40:59
 * @comment     消息表
 */

public interface IMessageInfoService extends ISuperService<MessageInfo>{

    /**
     * 根据搜索条件查询消息表条数
     * @param search 搜索条件
     * @return 消息表条数
     */
    int queryCountBySearch(MessageInfoSearch search);

    /**
     * 根据搜索条件分页查询消息表对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 消息表对象集合
     */
    List<MessageInfoView> queryListViewBySearch(MessageInfoSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询消息表对象
     * @param search 搜索条件
     * @return 消息表对象
     */
    MessageInfoView queryModelViewBySearch(MessageInfoSearch search);

    /**
     * 根据id查询消息表对象
     * @param id 主键id
     * @return 消息表对象
     */
    MessageInfoView queryModelViewById(String id);

    /**
     * 保存一个消息表对象
     * @param messageInfo 消息表对象
     * @return 是否成功
     */
    boolean saveMessageInfo(MessageInfo messageInfo);

}