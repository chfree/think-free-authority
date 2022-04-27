package com.cditer.free.behavior.entity.viewmodel;

import com.cditer.free.behavior.entity.base.IBehaviorModel;
import com.cditer.free.behavior.entity.model.DataEditLog;
import lombok.Data;

@Data
public class DataEditLogView extends DataEditLog {
    private IBehaviorModel behaviorModel;
}
