package com.tennetcn.free.authority.apis;

import com.tennetcn.free.security.handle.IUploadIntceptor;
import com.tennetcn.free.core.utils.CommonApplicationContextUtil;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.message.WebResponseStatus;
import com.tennetcn.free.web.webapi.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-14 13:57
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/upload/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="文件上传",value ="文件上传相关的操作")
public class UploadApi extends AuthorityApi {

    @ApiOperation(value = "接收上传的文件")
    @PostMapping("accept")
    public BaseResponse accept(String category,MultipartFile file){
        String type="single";
        return doUloaded(type,category,new MultipartFile[]{file});
    }

    @ApiOperation(value = "接收上传的文件")
    @PostMapping("accepts")
    public BaseResponse accepts(String category,MultipartFile[] files){
        String type="multiple";

        return doUloaded(type,category,files);
    }

    private BaseResponse doUloaded(String type,String category,MultipartFile[] files){
        BaseResponse resp=new BaseResponse();
        resp.setMessage("暂时无法处理上传的文件");
        resp.setStatus(WebResponseStatus.DATA_ERROR);

        // 如何处理上传文件由具体的intceptor进行处理
        Map<String,IUploadIntceptor> uploadIntceptors = CommonApplicationContextUtil.getCurrentContext().getBeansOfType(IUploadIntceptor.class);
        if(uploadIntceptors!=null&&uploadIntceptors.values()!=null&&uploadIntceptors.values().size()>0){
            IUploadIntceptor execUploadIntceptor = uploadIntceptors.values().stream().filter(uploadIntceptor -> category.equals(uploadIntceptor.category())).findFirst().get();

            if(execUploadIntceptor!=null){
                resp = execUploadIntceptor.accept(type,category,files,getCurrentLogin());
            }
        }

        return resp;
    }
}
