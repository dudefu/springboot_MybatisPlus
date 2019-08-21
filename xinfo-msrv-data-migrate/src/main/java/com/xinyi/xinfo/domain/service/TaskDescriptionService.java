package com.xinyi.xinfo.domain.service;

public interface TaskDescriptionService {

	/**
	 * 获取任务列表
	 * 
	 * @return
	 */
	String getTaskDescriptionList();

	/**
	 * 通过tdTaskId获取任务详情
	 * 
	 * @param tdTaskId
	 * @return
	 */
	String getTaskDescriptionById(int tdTaskId);

	/**
	 * 增加任务
	 * @param tdTaskId
	 * @param tdSourceId
	 * @param tdMode
	 * @param tdIncrementColumn
	 * @param tdTableName
	 * @param tdColumns
	 * @param tdTargetTableName
	 * @param tdDispatch
	 * @param tdRemarks
	 * @return
	 */
	String addTaskDescription(int tdTaskId, int tdSourceId, String tdMode, String tdIncrementColumn, String tdTableName, String tdColumns, String tdTargetTableName, String tdDispatch, String tdRemarks);

	/**
	 * 修改任务
	 * @param tdSourceId
	 * @param tdMode
	 * @param tdIncrementColumn
	 * @param tdTableName
	 * @param tdColumns
	 * @param tdTargetTableName
	 * @param tdDispatch
	 * @param tdRemarks
	 * @return
	 */
	String modifyTaskDescription( int tdTaskId,int tdSourceId, String tdMode, String tdIncrementColumn, String tdTableName, String tdColumns, String tdTargetTableName, String tdDispatch, String tdRemarks);

	/**
	 * 删除任务
	 * 
	 * @param tdTaskId
	 * @return
	 */
	String deleteTaskDescription(int tdTaskId);

	/**
	 * 根据Id查询执行进度
	 *
	 * @return tdTaskId
	 */
	String queryTaskProgressRateById(int tdTaskId);

}
