package com.cditer.free.devops.data.entity.viewmodel;

import com.cditer.free.devops.data.entity.model.ConfigPropertie;
import lombok.Data;

@Data
public class ConfigPropertieView extends ConfigPropertie {
    /**
     * 应用名称
     */
    private String projectName;

    /**
     * 环境
     */
    private String profile;

    /**
     * 分支
     */
    private String label;
}
