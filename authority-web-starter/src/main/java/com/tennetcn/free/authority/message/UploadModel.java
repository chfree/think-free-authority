package com.tennetcn.free.authority.message;

import com.tennetcn.free.authority.data.entity.model.FileBsn;
import com.tennetcn.free.authority.data.entity.model.FileInfo;
import com.tennetcn.free.security.message.LoginModel;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
}
