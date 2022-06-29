package com.cditer.free.message.entity.viewmodel;

import com.cditer.free.message.entity.model.MessageInfo;
import lombok.Data;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 16:26:01
 * @comment     消息表
 */

@Data
public class MessageInfoSearch extends MessageInfo {
    /**
     * 查询模式
     */
    private String queryMode = "me";

    /**
     * 用户id
     */
    private String userId;

    /**
     * 是否已读
     */
    private String wthrRead = "01";
}