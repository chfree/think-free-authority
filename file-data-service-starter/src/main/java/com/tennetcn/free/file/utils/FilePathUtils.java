package com.tennetcn.free.file.utils;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.authority.entity.model.ParamSetting;
import com.tennetcn.free.authority.logical.service.IParamSettingService;
import com.tennetcn.free.core.exception.BizException;
import com.tennetcn.free.core.util.SpringContextUtils;
import com.tennetcn.free.file.data.enums.FileParamSettingKeys;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-19 20:22
 * @comment
 */

public class FilePathUtils {

    public static String delayPath = File.separator + "delay";


    private static IParamSettingService paramSettingService;

    private static IParamSettingService getParamSettingService(){
        if(paramSettingService==null){
            paramSettingService = SpringContextUtils.getCurrentContext().getBean(IParamSettingService.class);
        }
        return paramSettingService;
    }

    private static String pathExp=File.separator +"yyyy"+File.separator+"MM"+File.separator+"dd"+File.separator;

    public static String getFilePath(){
        return DateUtil.format(DateUtil.date(),pathExp);
    }

    public static String getFileChunkPath(){
        return DateUtil.format(DateUtil.date(),pathExp) + "fileChunk"+File.separator;
    }

    public static String getDiskPath(){
        ParamSetting paramSetting = getParamSettingService().queryModelByName(FileParamSettingKeys.UPLOAD_PATH);
        if(paramSetting==null|| StringUtils.isEmpty(paramSetting.getParamValue())){
            throw new BizException("无法获取上传文件的路径，请联系管理员;"+ FileParamSettingKeys.UPLOAD_PATH);
        }
        return paramSetting.getParamValue();
    }
}
