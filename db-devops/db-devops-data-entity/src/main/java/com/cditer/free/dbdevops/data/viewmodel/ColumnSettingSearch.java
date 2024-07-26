package com.cditer.free.dbdevops.data.viewmodel;


import com.cditer.free.dbdevops.data.model.ColumnSetting;
import lombok.Data;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-07-25 12:57:19
 * @comment     列信息配置
 */

@Data
public class ColumnSettingSearch extends ColumnSetting {
    /**
     * not-id
     */
    private String notId;

}