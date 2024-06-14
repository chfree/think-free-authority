package com.cditer.free.param.logical.mapper;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.param.data.entity.model.TableSetting;
import com.cditer.free.param.data.entity.viewmodel.TableSettingSearch;
import com.cditer.free.param.data.entity.viewmodel.TableSettingView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-06-14 12:29:35
 * @comment     表信息配置
 */

@Mapper
public interface ITableSettingMapper extends IMapper<TableSetting>{

    int queryCountBySearch(@Param("search") TableSettingSearch search);

    List<TableSettingView> queryListViewBySearch(@Param("search") TableSettingSearch search, @Param("pager") PagerModel pagerModel);

    TableSettingView queryModelViewBySearch(@Param("search") TableSettingSearch search);

}
