package com.tennetcn.free.authority.dao.impl;


import com.tennetcn.free.authority.dao.IFileInfoDao;
import com.tennetcn.free.authority.data.entity.model.FileInfo;
import com.tennetcn.free.authority.data.entity.viewmodel.FileInfoSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
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

    private void appendExpression(ISqlExpression sqlExpression, FileInfoSearch search){
        sqlExpression.andEqNoEmpty("file_id",search.getFileId());

        sqlExpression.andNotEqNoEmpty("file_id",search.getNotFileId());

        sqlExpression.andEqNoEmpty("size",search.getSize());

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