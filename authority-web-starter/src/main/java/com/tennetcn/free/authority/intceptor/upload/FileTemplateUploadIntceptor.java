package com.tennetcn.free.authority.intceptor.upload;

import com.tennetcn.free.authority.data.entity.model.FileTemplate;
import com.tennetcn.free.authority.data.enums.UploadType;
import com.tennetcn.free.authority.handle.IUploadIntceptor;
import com.tennetcn.free.authority.message.UploadIntceptorParam;
import com.tennetcn.free.authority.message.UploadModel;
import com.tennetcn.free.authority.service.IFileTemplateService;
import com.tennetcn.free.core.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-15 12:42
 * @comment
 */

@Component
public class FileTemplateUploadIntceptor implements IUploadIntceptor {

    @Autowired
    IFileTemplateService fileTemplateService;

    @Override
    public void accept(UploadIntceptorParam uploadIntceptorParam) {
        // 一次只能上传一个
        if(!UploadType.SINGLE.equals(uploadIntceptorParam.getType())){
            throw new BizException("只允许上传单个文件");
        }

        String bsnId = uploadIntceptorParam.getBsnId();
        if(StringUtils.isEmpty(bsnId)){
            throw new BizException("文件模板id不能为空");
        }
        UploadModel uploadModel = uploadIntceptorParam.getUploadModels().get(0);

        FileTemplate fileTemplate = fileTemplateService.queryModel(bsnId);
        if(fileTemplate==null){
            throw new BizException("创建的文件模板id找不到对应的信息:" + bsnId);
        }
        fileTemplate.setFileId(uploadModel.getFileInfo().getFileId());

        fileTemplateService.updateModel(fileTemplate);
    }

    @Override
    public String getBsnType() {
        return "fileTemplate";
    }
}
