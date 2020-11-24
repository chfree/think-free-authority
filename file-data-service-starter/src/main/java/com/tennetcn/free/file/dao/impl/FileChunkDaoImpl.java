package com.tennetcn.free.file.dao.impl;

import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import com.tennetcn.free.file.dao.IFileChunkDao;
import com.tennetcn.free.file.data.entity.model.FileChunk;
import com.tennetcn.free.file.data.entity.viewmodel.FileChunkSearch;
import org.springframework.stereotype.Component;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-11-22 19:27:51
 * @comment     文件块
 */

@Component
public class FileChunkDaoImpl extends SuperDao<FileChunk> implements IFileChunkDao {
    @Override
    public int queryCountBySearch(FileChunkSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(FileChunk.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<FileChunk> queryListBySearch(FileChunkSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(FileChunk.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, FileChunkSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("identifier",search.getIdentifier());

        sqlExpression.andEqNoEmpty("filename",search.getFilename());

        sqlExpression.andEqNoEmpty("mime_type",search.getMimeType());

        sqlExpression.andEqNoEmpty("suffix",search.getSuffix());

        sqlExpression.andEqNoEmpty("bsn_id",search.getBsnId());

        sqlExpression.andEqNoEmpty("bsn_type",search.getBsnType());

        sqlExpression.andEqNoEmpty("status",search.getStatus());

    }
}