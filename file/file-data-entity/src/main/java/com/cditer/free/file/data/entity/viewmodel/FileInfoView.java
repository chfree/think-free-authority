package com.cditer.free.file.data.entity.viewmodel;

import com.cditer.free.file.data.entity.model.FileInfo;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-12-02 12:34
 * @comment
 */

@Data
public class FileInfoView extends FileInfo {
    private Integer seqIndex;

    public String getSeqDisplayName(){
        if(this.getSeqIndex()<=0){
            return this.getDisplayName();
        }
        if(StringUtils.isEmpty(this.getSuffix())){
            return this.getDisplayName() + "("+this.getSeqIndex()+")";
        }
        return this.getDisplayName().replace("."+this.getSuffix(),"") + "("+this.getSeqIndex()+")."+this.getSuffix();
    }
}
