package com.xinyi.xinfo.domain.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinyi.xinfo.domain.model.DataSource;

public interface DataSourceMapper extends BaseMapper<DataSource> {
    /**
     * 添加数据源
     * @param ds
     */
    void addDataSource(DataSource ds);


}
