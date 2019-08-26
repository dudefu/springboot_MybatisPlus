package com.xinyi.xinfo.domain.service;

public interface TaskExecuteService {

    /**
     * 批量任务执行
     * @return
     */
    int batchExecuteDatax();

    /**
     * 单个任务执行
     * @param tdTaskId
     * @return
     */
    int SingleExecuteDatax(int tdTaskId);
}
