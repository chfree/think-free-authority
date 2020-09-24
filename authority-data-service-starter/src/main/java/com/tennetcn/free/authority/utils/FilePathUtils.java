package com.tennetcn.free.authority.utils;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.authority.data.entity.model.ParamSetting;
import com.tennetcn.free.authority.data.enums.ParamSettingKeys;
import com.tennetcn.free.authority.service.IParamSettingService;
import com.tennetcn.free.core.exception.BizException;
import com.tennetcn.free.core.util.SpringContextUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-19 20:22
 * @comment
 */

public class FilePathUtils {

    public static String delayPath = "/delay";


    private static IParamSettingService paramSettingService;

    private static IParamSettingService getParamSettingService(){
        if(paramSettingService==null){
            paramSettingService = SpringContextUtils.getCurrentContext().getBean(IParamSettingService.class);
        }
        return paramSettingService;
    }

    private static String pathExp="/yyyy/MM/dd/";

    public static String getFilePath(){
        return DateUtil.format(DateUtil.date(),pathExp);
    }

    public static String getDiskPath(){
        ParamSetting paramSetting = getParamSettingService().queryModelByName(ParamSettingKeys.UPLOAD_PATH);
        if(paramSetting==null|| StringUtils.isEmpty(paramSetting.getParamValue())){
            throw new BizException("无法获取上传文件的路径，请联系管理员;"+ParamSettingKeys.UPLOAD_PATH);
        }
        return paramSetting.getParamValue();
    }
}
