package com.cditer.free.file.dao.impl;

import com.cditer.free.file.data.entity.viewmodel.FileInfoSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.file.dao.IFileInfoDao;
import com.cditer.free.file.data.entity.model.FileInfo;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:20:45
 * @comment     文件信息表
 */

@Component
public class FileInfoDaoImpl extends SuperDao<FileInfo> implements IFileInfoDao {
    @Override
    public int queryCountBySearch(FileInfoSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(FileInfo.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<FileInfo> queryListBySearch(FileInfoSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(FileInfo.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public FileInfo queryModelBySearch(FileInfoSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(FileInfo.class);

        appendExpression(sqlExpression,search);

        return queryModel(sqlExpression);
    }

    private void appendExpression(ISqlExpression sqlExpression, FileInfoSearch search){
        sqlExpression.andEqNoEmpty("file_id",search.getFileId());

        sqlExpression.andNotEqNoEmpty("file_id",search.getNotFileId());

        sqlExpression.andEqNoEmpty("mime_type",search.getMimeType());

        sqlExpression.andEqNoEmpty("sha1",search.getSha1());

        sqlExpression.andEqNoEmpty("store_type",search.getStoreType());

        sqlExpression.andEqNoEmpty("suffix",search.getSuffix());

        sqlExpression.andEqNoEmpty("path",search.getPath());

        sqlExpression.andEqNoEmpty("display_name",search.getDisplayName());

        sqlExpression.andEqNoEmpty("upload_user_id",search.getUploadUserId());

        sqlExpression.andEqNoEmpty("upload_user_name",search.getUploadUserName());

        sqlExpression.andEqNoEmpty("delete_mark",search.getDeleteMark());

    }
}