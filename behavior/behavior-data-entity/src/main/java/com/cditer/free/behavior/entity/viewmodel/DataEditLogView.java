package com.cditer.free.behavior.entity.viewmodel;

import com.cditer.free.behavior.entity.model.DataEditLog;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
public class DataEditLogView extends DataEditLog {
    public boolean isRecord(){
        return StringUtils.hasText(this.getBsnId());
    }

    private boolean recordDtl;

    private String bsnIdFieldName;

    private List<DataEditDtlView> dataEditDtlViewList;
}
