package com.tennetcn.free.authority.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import cn.hutool.core.convert.Convert;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.tennetcn.free.core.message.data.ModelBase;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 11:26:08
 * @comment     参数配置表
 */

@Data
@Entity
@Table(name="base_param_setting")
public class ParamSetting extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 参数名称
     */
    @Column(name="name")
    private String name;

    /**
     * 参数标题
     */
    @Column(name="title")
    private String title;


    /**
     * 值类型
     */
    @Column(name="val_type")
    private String valType;

    /**
     * 参数值
     */
    @Column(name="param_value")
    private String paramValue;

    /**
     * 备注
     */
    @Column(name="comments")
    private String comments;

    @JsonIgnore
    public int getIntValue(){
        return Integer.parseInt(this.paramValue);
    }

    @JsonIgnore
    public int getIntValue(int defaultValue){
        return Convert.toInt(this.paramValue,defaultValue);
    }

    @JsonIgnore
    public double getDoubleValue(){
        return Double.parseDouble(this.paramValue);
    }

    @JsonIgnore
    public double getDoubleValue(double defaultValue){
        return Convert.toDouble(this.paramValue,defaultValue);
    }

    @JsonIgnore
    public float getFloatValue(){
        return Float.parseFloat(this.paramValue);
    }

    @JsonIgnore
    public float getFloatValue(float defaultValue){
        return Convert.toFloat(this.paramValue,defaultValue);
    }

}