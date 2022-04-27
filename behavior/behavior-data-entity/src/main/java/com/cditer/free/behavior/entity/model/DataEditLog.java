package com.cditer.free.behavior.entity.model;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-04-27 21:02:49
 * @comment     数据修改记录
 */

@Data
@Entity
@Table(name="bhv_data_edit_log")
public class DataEditLog extends ModelBase {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 业务类型
     */
    @Column(name="bsn_type")
    private String bsnType;

    /**
     * 业务id
     */
    @Column(name="bsn_id")
    private String bsnId;

    /**
     * 操作类型
     */
    @Column(name="oper_type")
    private String operType;

    /**
     * 记录时间
     */
    @Column(name="record_dt")
    private Date recordDt;

    /**
     * 用户id
     */
    @Column(name="user_id")
    private String userId;

    /**
     * 用户名称
     */
    @Column(name="user_name")
    private String userName;

    /**
     * 用户部门id
     */
    @Column(name="user_dept_id")
    private String userDeptId;

    /**
     * 用户部门名称
     */
    @Column(name="user_dept_name")
    private String userDeptName;

    /**
     * 用户角色id
     */
    @Column(name="user_role_id")
    private String userRoleId;

    /**
     * 用户角色名称
     */
    @Column(name="user_role_name")
    private String userRoleName;

}