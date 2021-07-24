package com.cditer.free.file.message;

import com.cditer.free.file.data.entity.model.FileBsn;
import com.cditer.free.file.data.entity.model.FileInfo;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-08-26 16:35
 * @comment
 */

@Data
public class UploadModel {
    private FileInfo fileInfo;

    private MultipartFile file;

    private FileBsn fileBsn;

    private InputStream inputStream;
}
