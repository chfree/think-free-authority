package com.tennetcn.free.file.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.file.dao.IFileCatalogDao;
import com.tennetcn.free.file.data.entity.model.FileCatalog;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogSearch;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogTree;
import com.tennetcn.free.file.data.enums.CatalogScope;
import com.tennetcn.free.file.service.IFileCatalogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-06 22:17:50
 * @comment     文件目录表
 */

@Component
public class FileCatalogServiceImpl extends SuperService<FileCatalog> implements IFileCatalogService{
    @Autowired
    IFileCatalogDao fileCatalogDao;

    @Override
    public int queryCountBySearch(FileCatalogSearch search) {
        return fileCatalogDao.queryCountBySearch(search);
    }

    @Override
    public List<FileCatalog> queryListBySearch(FileCatalogSearch search, PagerModel pagerModel) {
        return fileCatalogDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public List<FileCatalog> queryListByTopLevel(FileCatalogSearch search) {
        return fileCatalogDao.queryListByTopLevel(search);
    }

    @Override
    public List<FileCatalog> queryListByTwoLevel(List<String> topIds, FileCatalogSearch search) {
        return fileCatalogDao.queryListByTwoLevel(topIds,search);
    }

    @Override
    public List<FileCatalogTree> queryListByOwnShow(String userId) {
        // 先查出top的，top都是global的
        FileCatalogSearch topSearch = new FileCatalogSearch();
        topSearch.setScope(CatalogScope.GLOBAL);
        List<FileCatalog> topCatalogs = queryListByTopLevel(topSearch);
        if(topCatalogs==null||topCatalogs.isEmpty()){
            return null;
        }

        List<String> topIds = topCatalogs.stream().map(item -> item.getId()).collect(Collectors.toList());

        /**
         * 查出公共的二级
         */
        FileCatalogSearch twoGlobalSearch = new FileCatalogSearch();
        twoGlobalSearch.setScope(CatalogScope.GLOBAL);
        List<FileCatalog> twoGlobalCatalogs = queryListByTwoLevel(topIds,twoGlobalSearch);

        /**
         * 查出子级的二级
         */
        FileCatalogSearch twoPersonSearch = new FileCatalogSearch();
        twoPersonSearch.setScope(CatalogScope.PERSON);
        twoPersonSearch.setUserId(userId);
        List<FileCatalog> twoPersonCatalogs = queryListByTwoLevel(topIds,twoPersonSearch);

        List<FileCatalog> allTwoList = new ArrayList<>();
        allTwoList.addAll(twoGlobalCatalogs);
        allTwoList.addAll(twoPersonCatalogs);


        List<FileCatalogTree> topCatalogTrees = topCatalogs.stream()
                .map(item -> {
                    FileCatalogTree childrenTree = new FileCatalogTree();
                    BeanUtils.copyProperties(item, childrenTree);

                    List<String> pathNames = Stream.of(item.getName()).collect(Collectors.toList());
                    childrenTree.setPathNames(pathNames);

                    return childrenTree;
                })
                .collect(Collectors.toList());

        loopFileCatalog(topCatalogTrees, allTwoList);

        return topCatalogTrees;
    }

    private void loopFileCatalog(List<FileCatalogTree> parentCatalogs,List<FileCatalog> allCatalog){
        if(parentCatalogs==null||parentCatalogs.isEmpty()){
            return;
        }
        if(allCatalog==null||allCatalog.isEmpty()){
            return;
        }
        for (FileCatalogTree parentCatalog : parentCatalogs) {
            List<FileCatalogTree> children = allCatalog.stream()
                    .filter(item -> parentCatalog.getId().equals(item.getParentId()))
                    .map(item -> {
                        FileCatalogTree childrenTree = new FileCatalogTree();
                        BeanUtils.copyProperties(item,childrenTree);

                        // 设置父级名称
                        childrenTree.setParentName(parentCatalog.getName());

                        // 设置父级名称数组
                        List<String> pathNames = new ArrayList<>();
                        pathNames.addAll(parentCatalog.getPathNames());
                        pathNames.add(item.getName());
                        childrenTree.setPathNames(pathNames);

                        return childrenTree;
                    }).collect(Collectors.toList());

            parentCatalog.setChildren(children);

            loopFileCatalog(children, allCatalog);
        }
    }

}