package com.cditer.free.behavior.entity.viewmodel;

import com.cditer.free.behavior.entity.model.WebVisitLog;
import lombok.Data;

@Data
public class WebVisitLogView extends WebVisitLog {
    private String userName;

    private String roleName;
}
