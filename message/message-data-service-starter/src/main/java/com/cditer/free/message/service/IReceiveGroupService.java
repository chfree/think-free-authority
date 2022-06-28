package com.cditer.free.message.service;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.message.entity.model.ReceiveGroup;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupSearch;
import com.cditer.free.message.entity.viewmodel.ReceiveGroupView;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:33:43
 * @comment     消息组
 */

public interface IReceiveGroupService extends ISuperService<ReceiveGroup>{

    /**
     * 根据搜索条件查询消息组条数
     * @param search 搜索条件
     * @return 消息组条数
     */
    int queryCountBySearch(ReceiveGroupSearch search);

    /**
     * 根据搜索条件分页查询消息组对象集合
     * @param search 搜索条件
     * @param pagerModel 分页条件
     * @return 消息组对象集合
     */
    List<ReceiveGroupView> queryListViewBySearch(ReceiveGroupSearch search, PagerModel pagerModel);

    /**
     * 根据搜索条件查询消息组对象
     * @param search 搜索条件
     * @return 消息组对象
     */
    ReceiveGroupView queryModelViewBySearch(ReceiveGroupSearch search);

    /**
     * 根据id查询消息组对象
     * @param id 主键id
     * @return 消息组对象
     */
    ReceiveGroupView queryModelViewById(String id);

    /**
     * 保存一个消息组对象
     * @param receiveGroup 消息组对象
     * @return 是否成功
     */
    boolean savereceiveGroup(ReceiveGroup receiveGroup);

}