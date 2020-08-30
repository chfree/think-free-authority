package com.tennetcn.free.authority.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.tennetcn.free.authority.data.entity.model.FileBsn;
import com.tennetcn.free.authority.data.entity.model.FileInfo;
import com.tennetcn.free.authority.data.entity.model.ParamSetting;
import com.tennetcn.free.authority.data.enums.FileStoreType;
import com.tennetcn.free.authority.data.enums.ParamSettingKeys;
import com.tennetcn.free.authority.data.enums.UploadType;
import com.tennetcn.free.authority.message.UploadIntceptorParam;
import com.tennetcn.free.authority.message.UploadModel;
import com.tennetcn.free.authority.service.IFileBsnService;
import com.tennetcn.free.authority.service.IFileInfoService;
import com.tennetcn.free.authority.service.IParamSettingService;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.enums.YesOrNo;
import com.tennetcn.free.core.exception.BizException;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.core.util.PkIdUtils;
import com.tennetcn.free.core.util.SpringContextUtils;
import com.tennetcn.free.authority.handle.IUploadIntceptor;
import com.tennetcn.free.core.util.StringHelper;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.message.WebResponseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-14 13:57
 * @comment
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/authority/upload/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="文件上传",value ="文件上传相关的操作")
public class UploadApi extends AuthorityApi {

    @Autowired
    IFileInfoService fileInfoService;

    @Autowired
    IParamSettingService paramSettingService;

    @Autowired
    IFileBsnService fileBsnService;

    @ApiOperation(value = "接收上传的文件")
    @PostMapping("accept")
    @Transactional
    public BaseResponse accept(String bsnType,String bsnId,String bsnJson, MultipartFile file){
        return doUloaded(UploadType.SINGLE,bsnType,bsnId,bsnJson,new MultipartFile[]{file});
    }

    @ApiOperation(value = "接收上传的文件")
    @PostMapping("accepts")
    @Transactional
    public BaseResponse accepts(String bsnType,String bsnId,String bsnJson,MultipartFile[] files){
        return doUloaded(UploadType.MULTIPLE,bsnType,bsnId,bsnJson,files);
    }

    private BaseResponse doUloaded(String type,String bsnType,String bsnId,String bsnJson,MultipartFile[] files){
        BaseResponse resp=new BaseResponse();

        List<String> ids = new ArrayList<>();

        List<UploadModel> uploadModels = new ArrayList<>();
        for (MultipartFile file : files) {
            FileInfo fileInfo = saveFileInfo(bsnType, bsnId, file);
            FileBsn fileBsn = saveFileBsn(fileInfo,file, bsnType, bsnId);

            UploadModel uploadModel = new UploadModel();

            try {
                if(ModelStatus.add.equals(fileInfo.getModelStatus())){
                    File saveFile = saveFileToDisk(fileInfo, file);
                    uploadModel.setInputStream(new FileInputStream(saveFile));
                }else{
                    // 已经存在,file中的inputStream还没有被读走
                    uploadModel.setInputStream(file.getInputStream());
                }
            } catch (IOException e) {
                log.info("处理文件流失败",e);
                new BizException("处理文件流失败",e);
            }

            uploadModel.setFile(file);
            uploadModel.setFileInfo(fileInfo);
            uploadModel.setFileBsn(fileBsn);

            uploadModels.add(uploadModel);

            ids.add(fileInfo.getFileId());
        }
        if("single".equals(type)){
            resp.put("id",ids.get(0));
        }else if("multiple".equals(type)){
            resp.put("ids",ids);
        }

        // 如何处理上传后的文件由具体的intceptor进行处理
        // todo chenghuan 此处可以考虑缓存起来，不用每次获取
        Map<String,IUploadIntceptor> uploadIntceptors = SpringContextUtils.getCurrentContext().getBeansOfType(IUploadIntceptor.class);
        if(uploadIntceptors==null&&uploadIntceptors.values()==null||uploadIntceptors.values().size()<=0){
            return resp;
        }

        Optional<IUploadIntceptor> first = uploadIntceptors.values().stream().filter(uploadIntceptor -> bsnType.equals(uploadIntceptor.getBsnType())).findFirst();
        if(!first.isPresent()){
            return resp;
        }
        IUploadIntceptor execUploadIntceptor = first.get();
        if(execUploadIntceptor!=null){
            UploadIntceptorParam param = new UploadIntceptorParam();
            param.setBsnType(bsnType);
            param.setType(type);
            param.setLoginModel(getCurrentLogin());
            param.setUploadModels(uploadModels);
            param.setResponse(resp);
            param.setBsnId(bsnId);
            param.setBsnJson(bsnJson);

            execUploadIntceptor.accept(param);
        }
        return resp;
    }

    private FileInfo saveFileInfo(String bsnType,String bsnId,MultipartFile file){
        FileInfo fileInfo = null;
        String sha1 = null;
        Digester sha1Digester = new Digester(DigestAlgorithm.SHA1);
        try {
            sha1 = sha1Digester.digestHex(file.getInputStream());
            fileInfo = fileInfoService.getFileInfoBySha1(sha1);
        } catch (IOException e) {
            log.error("获取文件的sha1时出错:"+fileInfo.getFileId(),e);
        }

        if(fileInfo==null){
            fileInfo = new FileInfo();
            fileInfo.setSha1(sha1);
            fileInfo.setDeleteMark(YesOrNo.NO);
            fileInfo.setFileId(PkIdUtils.getId());
            fileInfo.setDisplayName(file.getOriginalFilename());
            fileInfo.setMimeType(file.getContentType());
            fileInfo.setSize(file.getSize());
            fileInfo.setSuffix(StringHelper.fileExt(file.getOriginalFilename()));
            fileInfo.setUploadDate(DateUtil.date());
            fileInfo.setUploadUserId(getLoginId());
            fileInfo.setUploadUserName(getLoginName());
            fileInfo.setStoreType(FileStoreType.LOCAL);
            fileInfo.setPath(getFilePath());
            fileInfo.setModelStatus(ModelStatus.add);

            fileInfoService.addModel(fileInfo);
        } else {
            // 如果不为null，判断一下文件是否存在，不存在文件，可能被删了，在存储一下
            String localPath = getDiskPath() + fileInfo.getPath();
            File localPathFile = new File(localPath +"/"+ fileInfo.getFileName());
            if(!localPathFile.exists()){
                // 设置为add，在保存完fileInfo逻辑会进行add状态的本地文件保存
                fileInfo.setModelStatus(ModelStatus.add);
            }
        }

        return fileInfo;
    }



    private String pathExp="/yyyy/MM/dd/";

    private String getFilePath(){
        return DateUtil.format(DateUtil.date(),pathExp);
    }

    private String getDiskPath(){
        ParamSetting paramSetting = paramSettingService.queryModelByName(ParamSettingKeys.UPLOAD_PATH);
        if(paramSetting==null|| StringUtils.isEmpty(paramSetting.getParamValue())){
            throw new BizException("无法获取上传文件的路径，请联系管理员;"+ParamSettingKeys.UPLOAD_PATH);
        }
        return paramSetting.getParamValue();
    }

    private File saveFileToDisk(FileInfo fileInfo,MultipartFile file){
        String localPath = getDiskPath() + fileInfo.getPath();
        File localPathFile = new File(localPath);

        if (!localPathFile.exists()) {
            localPathFile.mkdirs();
        }
        File saveFile = new File(localPath, fileInfo.getFileName());
        try {
            file.transferTo(saveFile);
        }catch (Exception ex){
            log.error("保存文件到本地出错;路径:"+ localPath +"；文件id:"+ fileInfo.getFileId(),ex);
        }
        return saveFile;
    }

    private FileBsn saveFileBsn(FileInfo fileInfo,MultipartFile file,String bsnType,String bsnId){
        FileBsn fileBsn = new FileBsn();
        fileBsn.setFileId(fileInfo.getFileId());
        fileBsn.setId(PkIdUtils.getId());
        fileBsn.setDeleteMark(YesOrNo.NO);
        fileBsn.setBsnType(bsnType);
        fileBsn.setBsnId(bsnId);
        fileBsn.setDisplayName(file.getOriginalFilename());
        fileBsn.setUploadDate(DateUtil.date());
        fileBsn.setUploadUserId(getLoginId());
        fileBsn.setUploadUserName(getLoginName());

        fileBsnService.addModel(fileBsn);

        return fileBsn;
    }
}
