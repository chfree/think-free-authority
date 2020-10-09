package com.tennetcn.free.file.dao.impl;

import com.tennetcn.free.core.enums.OrderEnum;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import com.tennetcn.free.file.data.entity.model.FileCatalog;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogSearch;
import com.tennetcn.free.file.data.enums.FileDataKeys;
import org.springframework.stereotype.Component;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.file.dao.IFileCatalogDao;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-06 22:16:12
 * @comment     文件目录表
 */

@Component
public class FileCatalogDaoImpl extends SuperDao<FileCatalog> implements IFileCatalogDao{
    @Override
    public int queryCountBySearch(FileCatalogSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(FileCatalog.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<FileCatalog> queryListBySearch(FileCatalogSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(FileCatalog.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public List<FileCatalog> queryListByTopLevel(FileCatalogSearch search) {
        ISqlExpression topSql = SqlExpressionFactory.createExpression();
        topSql.selectAllFrom(FileCatalog.class)
                .andEq("parent_id",FileDataKeys.CATALOG_TOP)
                .addOrder("create_date", OrderEnum.asc);

        appendExpression(topSql,search);

        return queryList(topSql);
    }

    @Override
    public List<FileCatalog> queryListByTwoLevel(List<String> topIds,FileCatalogSearch search) {
        if(topIds==null||topIds.isEmpty()){
            return null;
        }
        ISqlExpression twoSql = SqlExpressionFactory.createExpression();
        twoSql.selectAllFrom(FileCatalog.class)
              .andWhereInString("parent_id",topIds)
              .addOrder("name", OrderEnum.asc);

        appendExpression(twoSql,search);

        return queryList(twoSql);
    }

    private void appendExpression(ISqlExpression sqlExpression, FileCatalogSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("user_id",search.getUserId());

        sqlExpression.andEqNoEmpty("parent_id",search.getParentId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("icon",search.getIcon());

        sqlExpression.andEqNoEmpty("scope",search.getScope());

        sqlExpression.andEqNoEmpty("mark",search.getMark());

        sqlExpression.andEqNoEmpty("comments",search.getComments());
    }
}