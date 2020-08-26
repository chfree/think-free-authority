package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IParamSettingDao;
import com.tennetcn.free.authority.data.entity.model.ParamSetting;
import com.tennetcn.free.authority.data.entity.viewmodel.ParamSettingSearch;
import com.tennetcn.free.authority.service.IParamSettingService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 11:30:21
 * @comment     参数配置表
 */

@Component
public class ParamSettingServiceImpl extends SuperService<ParamSetting> implements IParamSettingService {
    @Autowired
    IParamSettingDao paramSettingDao;

    @Override
    public int queryCountBySearch(ParamSettingSearch search) {
        return paramSettingDao.queryCountBySearch(search);
    }

    @Override
    public List<ParamSetting> queryListBySearch(ParamSettingSearch search, PagerModel pagerModel) {
        return paramSettingDao.queryListBySearch(search,pagerModel);
    }

}