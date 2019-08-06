package com.tennetcn.free.authority.apimodel.business;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.authority.model.Business;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.data.enums.YesOrNo;
import lombok.Data;
import org.junit.Test;

import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:54
 * @comment
 */

@Data
public class SaveBusinessReq extends Business {

    @NotBlank
    private String name;

    @NotBlank
    private String shortName;

    @Override
    public void setModelStatus(ModelStatus modelStatus) {
        super.setModelStatus(modelStatus);

        if(modelStatus==ModelStatus.add){
            setCreateDate(DateUtil.date());
        }else if(modelStatus==ModelStatus.update){
            setModifyDate(DateUtil.date());
        }
    }
}
