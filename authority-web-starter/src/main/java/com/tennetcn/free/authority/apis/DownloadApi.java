package com.tennetcn.free.authority.apis;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.tennetcn.free.authority.data.entity.model.FileInfo;
import com.tennetcn.free.authority.service.IFileInfoService;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.annotation.ApiAuthPassport;
import com.tennetcn.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-18 22:05
 * @comment
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/authority/download/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="下载上传的文件",value ="下载上传的文件相关的操作")
public class DownloadApi extends AuthorityApi {

    @Autowired
    IFileInfoService fileInfoService;

    @ApiOperation(value = "根据文件编号获取下载编号")
    @GetMapping("getDownId")
    public BaseResponse getDownId(@Valid @NotBlank(message = "文件id不能为空") String fileId){
        BaseResponse response = new BaseResponse();

        String downId = IdUtil.randomUUID();
        cached.put(downId,fileId);

        response.put("downId",downId);

        return response;
    }

    @ApiAuthPassport
    @ApiOperation(value = "根据下载编号下载文件")
    @GetMapping("downFile")
    public void downFile(@Valid @NotBlank(message = "文件id不能为空") String downId){
        Object fileId = cached.get(downId);
        HttpServletResponse servletResponse = getServletResponse();

        if(fileId==null){
            try {
                OutputStream outputStream = servletResponse.getOutputStream();
                outputStream.write("downId异常，找不到对应的文件id".getBytes());

                outputStream.flush();
                outputStream.close();
            }catch (Exception ex){
                log.error("", ex);
            }
            return;
        }

        FileInfo fileInfo = fileInfoService.queryModel(fileId.toString());

    }
}
