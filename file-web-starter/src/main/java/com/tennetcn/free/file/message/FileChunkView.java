package com.tennetcn.free.file.message;

import com.tennetcn.free.file.data.entity.model.FileChunk;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-11-21 20:20
 * @comment
 */

@Data
public class FileChunkView extends FileChunk {
    /**
     * 要上传的文件
     */
    private MultipartFile file;
}
