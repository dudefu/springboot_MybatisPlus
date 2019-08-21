package com.xinyi.xinfo.domain.repository;

import com.xinyi.xinfo.domain.model.TaskDescription;
import com.xinyi.xinfo.domain.model.TaskProgressRate;

import java.util.List;

public interface TaskDescriptionMapper {

    /**
     * 列出任务详情
     *
     * @return taskDescriptionList
     */
    List<TaskDescription> queryTaskDescription();

    /**
     * 根据Id列出具体任务
     *
     * @return tdTaskId
     */
    TaskDescription queryTaskDescriptionById(int tdTaskId);

    /**
     * 插入一条任务
     *
     * @param taskDescription
     * @return
     */
    int insertTaskDescription(TaskDescription taskDescription);

    /**
     * 更新一条任务
     *
     * @param taskDescription
     * @return
     */
    int updateTaskDescription(TaskDescription taskDescription);

    /**
     * 根据taskId,删除一条任务
     *
     * @param taskId
     * @return
     */
    int deleteTaskDescription(int taskId);

    /**
     * 根据Id查询执行进度
     *
     * @return tdTaskId
     */
    TaskProgressRate queryTaskProgressRateById(int tdTaskId);
}
