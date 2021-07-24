package com.cditer.free.file.dao.impl;

import com.cditer.free.file.data.entity.viewmodel.FileDeleteWaitSearch;
import com.cditer.free.file.mapper.IFileDeleteWaitMapper;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.file.dao.IFileDeleteWaitDao;
import com.cditer.free.file.data.entity.model.FileDeleteWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-21 12:00:35
 * @comment     文件删除等待表
 */

@Component
public class FileDeleteWaitDaoImpl extends SuperDao<FileDeleteWait> implements IFileDeleteWaitDao {

    @Autowired
    IFileDeleteWaitMapper fileDeleteWaitMapper;

    @Override
    public int queryCountBySearch(FileDeleteWaitSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(FileDeleteWait.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<FileDeleteWait> queryListBySearch(FileDeleteWaitSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(FileDeleteWait.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public List<FileDeleteWait> queryCanDeleteFile(PagerModel pagerModel) {
        return fileDeleteWaitMapper.queryCanDeleteFile(pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, FileDeleteWaitSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("file_id",search.getFileId());

        sqlExpression.andEqNoEmpty("size",search.getSize());

        sqlExpression.andEqNoEmpty("mime_type",search.getMimeType());

        sqlExpression.andEqNoEmpty("sha1",search.getSha1());

        sqlExpression.andEqNoEmpty("store_type",search.getStoreType());

        sqlExpression.andEqNoEmpty("suffix",search.getSuffix());

        sqlExpression.andEqNoEmpty("path",search.getPath());

        sqlExpression.andEqNoEmpty("bsn_id",search.getBsnId());

        sqlExpression.andEqNoEmpty("bsn_type",search.getBsnType());

        sqlExpression.andEqNoEmpty("upload_user_id",search.getUploadUserId());

        sqlExpression.andEqNoEmpty("upload_user_name",search.getUploadUserName());

        sqlExpression.andEqNoEmpty("display_name",search.getDisplayName());

        sqlExpression.andEqNoEmpty("wait_day",search.getWaitDay());

    }
}