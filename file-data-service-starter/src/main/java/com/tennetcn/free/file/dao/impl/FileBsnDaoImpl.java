package com.tennetcn.free.file.dao.impl;

import com.tennetcn.free.core.enums.OrderEnum;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import com.tennetcn.free.file.dao.IFileBsnDao;
import com.tennetcn.free.file.data.entity.model.FileBsn;
import com.tennetcn.free.file.data.entity.model.FileInfo;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnSearch;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnView;
import com.tennetcn.free.file.mapper.IFileBsnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:25:56
 * @comment     文件业务表
 */

@Component
public class FileBsnDaoImpl extends SuperDao<FileBsn> implements IFileBsnDao {

    @Autowired
    IFileBsnMapper fileBsnMapper;

    @Override
    public int queryCountBySearch(FileBsnSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(FileBsn.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public int queryViewCountBySearch(FileBsnSearch search) {
        ISqlExpression countSql = SqlExpressionFactory.createExpression();
        countSql.selectCount().from(FileBsn.class,"fileBsn")
                .leftJoin(FileInfo.class,"fileInfo").on("fileBsn.file_id","fileInfo.file_id")
                .setMainTableAlias("fileBsn");

        appendExpression(countSql,search);

        return queryCount(countSql);
    }

    @Override
    public List<FileBsn> queryListBySearch(FileBsnSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(FileBsn.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public FileBsn queryModelBySearch(FileBsnSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(FileBsn.class);

        appendExpression(sqlExpression,search);

        return queryModel(sqlExpression);
    }

    @Override
    public List<FileBsnView> queryViewListBySearch(FileBsnSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();

        sqlExpression.selectAllFrom(FileBsn.class,"fileBsn")
                     .appendSelect("fileInfo.size","fileInfo.mime_type as mimeType","fileInfo.sha1")
                     .appendSelect("fileInfo.store_type as storeType","fileInfo.suffix","fileInfo.path")
                     .leftJoin(FileInfo.class,"fileInfo").on("fileBsn.file_id","fileInfo.file_id")
                     .setMainTableAlias("fileBsn")
                     .addOrder("fileBsn.upload_date", OrderEnum.desc);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel,FileBsnView.class);
    }

    @Override
    public FileBsnView queryViewModelBySearch(FileBsnSearch search) {
        List<FileBsnView> fileBsnViews = queryViewListBySearch(search, new PagerModel(1, 1));
        if(fileBsnViews==null||fileBsnViews.isEmpty()){
            return null;
        }
        return fileBsnViews.get(0);
    }

    @Override
    public boolean deleteModel(String bsnId, String fileId) {
        ISqlExpression deleteSql = SqlExpressionFactory.createExpression();
        deleteSql.delete().from(FileBsn.class).andEq("file_id",fileId).andEq("bsn_id",bsnId);

        return delete(deleteSql)>=0;
    }

    @Override
    public List<String> queryOneLinkFileId(List<String> fileIds) {
        return fileBsnMapper.queryOneLinkFileId(fileIds);
    }

    @Override
    public Integer queryNextSeqIndex(String bsnId) {
        return fileBsnMapper.queryNextSeqIndex(bsnId);
    }

    private void appendExpression(ISqlExpression sqlExpression, FileBsnSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("file_id",search.getFileId());

        sqlExpression.andEqNoEmpty("bsn_id",search.getBsnId());

        sqlExpression.andEqNoEmpty("bsn_type",search.getBsnType());

        sqlExpression.andEqNoEmpty("upload_user_id",search.getUploadUserId());

        sqlExpression.andEqNoEmpty("upload_user_name",search.getUploadUserName());

        sqlExpression.andEqNoEmpty("delete_mark",search.getDeleteMark());

        sqlExpression.andEqNoEmpty("display_name",search.getDisplayName());

        sqlExpression.andLikeNoEmpty("upload_user_name",search.getLikeUploadUserName());

        sqlExpression.andLikeNoEmpty("display_name",search.getLikeDisplayName());

        sqlExpression.andEqNoEmpty("fileInfo.mime_type",search.getMimeType());

        sqlExpression.andEqNoEmpty("fileInfo.sha1",search.getSha1());

        sqlExpression.andEqNoEmpty("fileInfo.suffix",search.getSuffix());

        if(search.getStartUploadDate()!=null){
            sqlExpression.andWhere("upload_date>=#{startUploadDate}").setParam("startUploadDate",search.getStartUploadDate());
        }

        if(search.getEndUploadDate()!=null){
            sqlExpression.andWhere("upload_date<=#{endUploadDate}").setParam("endUploadDate",search.getEndUploadDate());
        }



    }
}