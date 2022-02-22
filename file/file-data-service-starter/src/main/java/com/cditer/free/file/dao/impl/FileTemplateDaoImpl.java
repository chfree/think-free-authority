package com.cditer.free.file.dao.impl;

import com.cditer.free.file.data.entity.viewmodel.FileTemplateSearch;
import com.cditer.free.core.enums.OrderEnum;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.file.dao.IFileTemplateDao;
import com.cditer.free.file.data.entity.model.FileTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-14 23:31:01
 * @comment     文件模板表
 */

@Component
public class FileTemplateDaoImpl extends SuperDao<FileTemplate> implements IFileTemplateDao {
    @Override
    public int queryCountBySearch(FileTemplateSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(FileTemplate.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<FileTemplate> queryListBySearch(FileTemplateSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(FileTemplate.class).addOrder("upload_date", OrderEnum.DESC);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, FileTemplateSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("file_id",search.getFileId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("title",search.getTitle());

        sqlExpression.andEqNoEmpty("upload_user_id",search.getUploadUserId());

        sqlExpression.andEqNoEmpty("upload_user_name",search.getUploadUserName());

        sqlExpression.andLikeNoEmpty("upload_user_name",search.getLikeUploadUserName());

        sqlExpression.andLikeNoEmpty("title",search.getLikeTitle());

        sqlExpression.andLikeNoEmpty("name",search.getLikeName());
    }
}