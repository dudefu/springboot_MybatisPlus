package com.xinyi.xinfo.domain.repository;

import com.xinyi.xinfo.domain.model.DataSource;
import com.xinyi.xinfo.domain.model.TabDataMigrateSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 数据源表 Mapper 接口
 * </p>
 *
 * @author mingshen.wang
 * @since 2019-08-16
 */
public interface TabDataMigrateSourceMapper extends BaseMapper<TabDataMigrateSource> {

    /**
     * 添加数据源
     * @param ds
     */
    void addDataSource(DataSource ds);


}
