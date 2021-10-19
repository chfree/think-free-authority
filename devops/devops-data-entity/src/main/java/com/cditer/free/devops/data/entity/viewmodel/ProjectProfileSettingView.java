package com.cditer.free.devops.data.entity.viewmodel;

import com.cditer.free.devops.data.entity.model.ProjectProfileSetting;
import lombok.Data;

@Data
public class ProjectProfileSettingView extends ProjectProfileSetting {
    private String projectName;
}
