package com.cditer.free.file.inceptor;

import com.cditer.free.file.message.UploadIntceptorParam;
import com.cditer.free.file.message.UploadModel;
import com.cditer.free.file.data.entity.model.FileTemplate;
import com.cditer.free.file.data.enums.UploadType;
import com.cditer.free.file.handle.IUploadIntceptor;
import com.cditer.free.core.exception.BizException;
import com.cditer.free.file.service.IFileTemplateService;
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
