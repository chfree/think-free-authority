package com.cditer.free.behavior.entity.viewmodel;

import com.cditer.free.behavior.entity.model.DataEditLog;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class DataEditLogView extends DataEditLog {
    public boolean isRecord(){
        return StringUtils.hasText(this.getBsnId());
    }

    private boolean recordDtl;
}
