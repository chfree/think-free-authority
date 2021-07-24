package com.cditer.free.file.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.cditer.free.file.data.entity.viewmodel.FileBsnSearch;
import com.cditer.free.file.data.entity.viewmodel.FileChunkSearch;
import com.cditer.free.file.data.enums.FileParamSettingKeys;
import com.cditer.free.file.data.enums.FileStoreType;
import com.cditer.free.file.exception.FileBizException;
import com.cditer.free.file.handle.IUploadIntceptor;
import com.cditer.free.file.message.FileChunkView;
import com.cditer.free.file.message.UploadIntceptorParam;
import com.cditer.free.file.message.UploadModel;
import com.cditer.free.file.service.IFileBsnService;
import com.cditer.free.file.service.IFileChunkService;
import com.cditer.free.file.service.IFileDeleteWaitService;
import com.cditer.free.file.service.IFileInfoService;
import com.cditer.free.file.utils.FilePathUtils;
import com.cditer.free.param.logical.service.IParamSettingService;
import com.cditer.free.file.data.entity.model.FileBsn;
import com.cditer.free.file.data.entity.model.FileChunk;
import com.cditer.free.file.data.entity.model.FileDeleteWait;
import com.cditer.free.file.data.entity.model.FileInfo;
import com.cditer.free.file.data.enums.FileChunkStatus;
import com.cditer.free.file.data.enums.UploadType;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.enums.YesOrNo;
import com.cditer.free.core.exception.BizException;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.util.SpringContextUtils;
import com.cditer.free.core.util.StringHelper;
import com.cditer.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
@RequestMapping(value = "/api/v1/file/upload/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="文件上传",value ="文件上传相关的操作")
public class UploadApi extends AuthorityApi {

    @Autowired
    IFileInfoService fileInfoService;

    @Autowired
    IParamSettingService paramSettingService;

    @Autowired
    IFileBsnService fileBsnService;

    @Autowired
    IFileDeleteWaitService fileDeleteWaitService;

    @Autowired
    IFileChunkService fileChunkService;

    private Map<String, IUploadIntceptor> uploadIntceptors;

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
            FileInfo fileInfo = saveFileInfo(file);
            FileBsn fileBsn = saveFileBsn(fileInfo, bsnType, bsnId);

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
        Map<String,IUploadIntceptor> uploadIntceptors = getUploadIntceptors();
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

    private Map<String,IUploadIntceptor> getUploadIntceptors(){
        if(this.uploadIntceptors == null){
            this.uploadIntceptors = SpringContextUtils.getCurrentContext().getBeansOfType(IUploadIntceptor.class);
        }
        return this.uploadIntceptors;
    }

    private FileInfo saveFileInfo(MultipartFile file){
        FileInfo fileInfo = null;
        String sha1 = null;
        try {
            sha1 = DigestUtil.sha1Hex(file.getInputStream());
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
            fileInfo.setPath(FilePathUtils.getFilePath());
            fileInfo.setModelStatus(ModelStatus.add);

            fileInfoService.addModel(fileInfo);
        } else {
            // 如果不为null，判断一下文件是否存在，不存在文件，可能被删了，在存储一下
            String localPath = FilePathUtils.getDiskPath() + fileInfo.getPath();
            File localPathFile = new File(localPath +"/"+ fileInfo.getFileName());
            if(!localPathFile.exists()){
                // 设置为add，在保存完fileInfo逻辑会进行add状态的本地文件保存
                fileInfo.setModelStatus(ModelStatus.add);
            }
        }

        return fileInfo;
    }

    private File saveFileToDisk(FileInfo fileInfo,MultipartFile file){
        String localPath = FilePathUtils.getDiskPath() + fileInfo.getPath();
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

    private FileBsn saveFileBsn(FileInfo fileInfo,String bsnType,String bsnId){
        FileBsn fileBsn = new FileBsn();
        fileBsn.setFileId(fileInfo.getFileId());
        fileBsn.setId(PkIdUtils.getId());
        fileBsn.setDeleteMark(YesOrNo.NO);
        fileBsn.setBsnType(bsnType);
        fileBsn.setBsnId(bsnId);
        fileBsn.setDisplayName(fileInfo.getDisplayName());
        fileBsn.setUploadDate(DateUtil.date());
        fileBsn.setUploadUserId(getLoginId());
        fileBsn.setUploadUserName(getLoginName());

        fileBsnService.saveFileBsn(fileBsn);

        return fileBsn;
    }

    @ApiOperation(value = "删除上传的文件")
    @PostMapping("deleteFile")
    @Transactional
    public BaseResponse deleteFile(@Valid @NotEmpty(message = "文件业务id不能为空") String fileBsnId){
        BaseResponse response = new BaseResponse();

        doDeleteFile(fileBsnId,false);

        response.putResult("true");

        return response;
    }

    private void doDeleteFile(String fileBsnId,boolean delay){
        FileBsn fileBsn = fileBsnService.queryModel(fileBsnId);
        FileInfo fileInfo = fileInfoService.queryModel(fileBsn.getBsnId());
        if(fileInfo==null){
            throw new FileBizException("文件信息不存在");
        }

        if(delay) {
            saveFileDeleteWait(fileInfo,fileBsnId);
        }
        /**
         * 因为有文件的sha1记录
         * 所以判一下被引用的情况
         * 大于1，则只删除fileBsn记录
         * 否则还要删除文件记录
         */
        FileBsnSearch search = new FileBsnSearch();
        search.setFileId(fileInfo.getFileId());
        int fileRelCount = fileBsnService.queryCountBySearch(search);
        if(fileRelCount==1){
            // 将文件备份到延时目录
            fileDeleteWaitService.moveFileToDelayDir(fileInfo);

            fileInfoService.deleteFileToDisk(fileInfo);
            fileInfoService.deleteModel(fileInfo.getFileId());
        }
        fileBsnService.deleteModel(fileBsnId);
    }

    @ApiOperation(value = "延时删除上传的文件")
    @PostMapping("deleteFileDelay")
    @Transactional
    public BaseResponse deleteFileDelay(@Valid @NotEmpty(message = "文件业务id不能为空") String fileBsnId){
        BaseResponse response = new BaseResponse();

        doDeleteFile(fileBsnId,true);

        response.putResult("true");

        return response;
    }

    private boolean saveFileDeleteWait(FileInfo fileInfo,String fileBsnId){
        FileDeleteWait fileDeleteWait = new FileDeleteWait();

        // 先复制fileInfo
        BeanUtils.copyProperties(fileInfo,fileDeleteWait);

        FileBsn fileBsn = fileBsnService.queryModel(fileBsnId);

        BeanUtils.copyProperties(fileBsn,fileDeleteWait);

        fileDeleteWait.setAddDate(DateUtil.date());
        int fileDeleteDelayDays = paramSettingService.queryIntValue(FileParamSettingKeys.FILE_DELETE_DELAY_DAYS, 3);
        fileDeleteWait.setWaitDay(fileDeleteDelayDays);

        return fileDeleteWaitService.addModel(fileDeleteWait);
    }

    @ApiOperation(value = "分片上传文件")
    @PostMapping("uploadChunk")
    @Transactional
    public BaseResponse uploadChunk(FileChunkView chunk){
        BaseResponse resp = new BaseResponse();
        FileChunk fileChunk = new FileChunk();
        BeanUtils.copyProperties(chunk,fileChunk,"file");

        try {
            MultipartFile file = chunk.getFile();
            log.debug("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunk.getChunkNumber());

            saveChunk(chunk);

            byte[] bytes = file.getBytes();
            String diskPath = FilePathUtils.getDiskPath();
            Path path = Paths.get(chunk.generateChunkPath(diskPath));

            //文件写入指定路径
            Files.write(path, bytes);
            log.debug("文件 {} 写入成功, uuid:{}", chunk.getFilename(), chunk.getIdentifier());

            resp.put("result",true);
            resp.put("fileChunk", fileChunk);
            resp.put("needMerge", true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileBizException(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "检查分片上传文件")
    @GetMapping("uploadChunk")
    @Transactional
    public BaseResponse checkUploadChunk(FileChunkView chunk, HttpServletResponse response){
        BaseResponse resp = new BaseResponse();

        FileChunk fileChunk = new FileChunk();
        BeanUtils.copyProperties(chunk,fileChunk,"file");
        resp.put("fileChunk", fileChunk);

        // 先检查文件有没有，没有文件，在检查分片有没有
        FileInfo fileInfo = fileInfoService.getFileInfoBySha1(chunk.getIdentifier());
        if(fileInfo!=null){
            resp.put("hasType", "file");
            resp.put("fileInfo", fileInfo);
            resp.put("needMerge", false);
            return resp;
        }
        FileChunkSearch fileChunkSearch = new FileChunkSearch();
        fileChunkSearch.setIdentifier(chunk.getIdentifier());

        List<Integer> uploadChunks = fileChunkService.queryListUploadChunk(fileChunkSearch);
        if(uploadChunks.size()>0){
            resp.put("hasType", "chunk");
            resp.put("uploadChunks", uploadChunks);
            resp.put("needMerge", true);
            return resp;
        }
        return resp;
    }

    @ApiOperation(value = "保存业务逻辑文件信息")
    @PostMapping("saveFileByChunk")
    @Transactional
    public BaseResponse saveFileByChunk(FileChunkView chunk){
        BaseResponse resp = new BaseResponse();

        FileInfo fileInfo = fileInfoService.getFileInfoBySha1(chunk.getIdentifier());
        if(fileInfo==null){
            // 根据sha1进行文件合并,返回数据库中的分片信息，便于fileInfo的保存
            FileChunk dbFileChunk = saveChunkToDisk(chunk.getIdentifier());

            // 更新fileChunk的状态
            fileChunkService.updateStatusByIdentifier(FileChunkStatus.MERGED,chunk.getIdentifier());

            // 保存文件信息
            fileInfo = saveFileInfoByChunk(dbFileChunk);
        }
        saveFileBsn(fileInfo,chunk.getBsnType(),chunk.getBsnId());

        return resp;
    }

    private FileInfo saveFileInfoByChunk(FileChunk dbFileChunk){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setSha1(dbFileChunk.getIdentifier());
        fileInfo.setDeleteMark(YesOrNo.NO);
        fileInfo.setFileId(dbFileChunk.getId());
        fileInfo.setDisplayName(dbFileChunk.getFilename());
        fileInfo.setMimeType(dbFileChunk.getMimeType());
        fileInfo.setSize(dbFileChunk.getTotalSize());
        fileInfo.setSuffix(StringHelper.fileExt(dbFileChunk.getFilename()));
        fileInfo.setUploadDate(DateUtil.date());
        fileInfo.setUploadUserId(getLoginId());
        fileInfo.setUploadUserName(getLoginName());
        fileInfo.setStoreType(FileStoreType.LOCAL);
        fileInfo.setPath(FilePathUtils.getFilePath());
        fileInfo.setModelStatus(ModelStatus.add);

        fileInfoService.addModel(fileInfo);

        return fileInfo;
    }


    private FileChunk saveChunkToDisk(String sha1){
        FileChunkSearch fileChunkSearch = new FileChunkSearch();
        fileChunkSearch.setIdentifier(sha1);

        List<FileChunk> fileChunks = fileChunkService.queryListBySearch(fileChunkSearch,null);
        if(fileChunks==null||fileChunks.isEmpty()){
            throw new FileBizException("没有需要合并的文件分片信息");
        }
        FileChunk fileChunk = fileChunks.get(0);
        if(fileChunk.getTotalChunks()!=fileChunks.size()){
            throw new FileBizException("文件分片信息不正确，无法进行合并保存");
        }

        try {
            final String diskPath = FilePathUtils.getDiskPath();
            String targetFile = diskPath + FilePathUtils.getFilePath() +File.separator+ fileChunk.getId() +"."+fileChunk.getSuffix();
            Files.createFile(Paths.get(targetFile));

            // 得到所有的路径文件
            fileChunks.stream().sorted((o1, o2) -> {
                return o1.getChunkNumber().compareTo(o2.getChunkNumber());
            }).forEach(chunk -> {
                try {
                    Path path = Paths.get(diskPath+chunk.getChunkFullPath());
                    //以追加的形式写入文件
                    Files.write(Paths.get(targetFile), Files.readAllBytes(path), StandardOpenOption.APPEND);
                    //合并后删除该块
                    Files.delete(path);
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                    throw new FileBizException("合并文件信息出错",ex);
                }

            });
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            throw new FileBizException("合并文件信息出错",ex);
        }
        return fileChunk;
    }


    private void saveChunk(FileChunkView chunk){
        MultipartFile file = chunk.getFile();

        chunk.setMimeType(file.getContentType());
        chunk.setSuffix(StringHelper.fileExt(file.getOriginalFilename()));
        chunk.setUploadDate(DateUtil.date());
        chunk.setUploadUserId(getLoginId());
        chunk.setUploadUserName(getLoginName());
        chunk.setPath(FilePathUtils.getFileChunkPath());
        chunk.setStatus(FileChunkStatus.UPLOAD);

        chunk.setId(PkIdUtils.getId());
        fileChunkService.addModel(chunk);
    }
}
