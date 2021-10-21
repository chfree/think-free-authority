package com.cditer.free.devops.data.entity.viewmodel;

import com.cditer.free.devops.data.entity.model.ProjectPuburl;
import lombok.Data;

@Data
public class ProjectPuburlView extends ProjectPuburl {
    private String projectName;
}
