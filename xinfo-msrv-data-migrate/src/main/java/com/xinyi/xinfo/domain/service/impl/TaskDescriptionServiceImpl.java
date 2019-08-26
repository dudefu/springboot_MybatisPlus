package com.xinyi.xinfo.domain.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.xinyi.xinfo.bean.Status;
import com.xinyi.xinfo.domain.model.TaskDescription;
import com.xinyi.xinfo.domain.model.TaskProgressRate;
import com.xinyi.xinfo.domain.repository.DataSourceMapper;
import com.xinyi.xinfo.domain.repository.TaskDescriptionMapper;
import com.xinyi.xinfo.domain.service.TaskDescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TaskDescriptionServiceImpl implements TaskDescriptionService {

	private static final Logger logger = LoggerFactory.getLogger(TaskDescriptionServiceImpl.class);
	JSONObject jsonObject ;

	@Autowired
	private TaskDescriptionMapper taskDescriptionMapper;


	/**
	 * 获取任务列表
	 *
	 * @return
	 */
	@Override
	public String getTaskDescriptionList() {

		jsonObject = new JSONObject();
		List<TaskDescription> listTaskDescription ;
		try{
			listTaskDescription = taskDescriptionMapper.queryTaskDescription() ;
			if(listTaskDescription.size()>0){
				jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
				jsonObject.put("data",listTaskDescription);
				return jsonObject.toJSONString();
			}else{
				jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, "数据为空"));
				return jsonObject.toJSONString();
			}
		}catch (Exception e){
			e.printStackTrace();
			jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
			return jsonObject.toJSONString();
		}

	}

	/**
	 * 通过tdTaskId获取任务详情
	 *
	 * @param tdTaskId
	 * @return
	 */
	@Override
	public String getTaskDescriptionById(int tdTaskId) {

		jsonObject = new JSONObject();
		TaskDescription taskDescription ;
		try{
			taskDescription = taskDescriptionMapper.queryTaskDescriptionById(tdTaskId);
			if(taskDescription != null){
				jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
				jsonObject.put("data",taskDescription);
				return jsonObject.toJSONString();
			}else{
				jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, "数据为空,请确认taskId是否存在"));
				return jsonObject.toJSONString();
			}
		}catch (Exception e){
			e.printStackTrace();
			jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
			return jsonObject.toJSONString();
		}
	}

	/**
	 * 增加任务
	 *
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
	@Transactional
	@Override
	public String addTaskDescription(int tdTaskId, int tdSourceId, String tdMode, String tdIncrementColumn, String tdTableName, String tdColumns, String tdTargetTableName, String tdDispatch, String tdRemarks,String tdJobjsonFilename,int tdJobjsonstate) {

		jsonObject = new JSONObject();
		TaskDescription taskDescription = new TaskDescription(tdTaskId,  tdSourceId,  tdMode,  tdIncrementColumn,  tdTableName,  tdColumns,  tdTargetTableName,  tdDispatch,  tdRemarks,tdJobjsonFilename,tdJobjsonstate);
		if (taskDescription.getTdTaskId() != 0 && 0 != taskDescription.getTdSourceId() &&
			null != taskDescription.getTdMode() && null != taskDescription.getTdTableName() &&
			null != taskDescription.getTdColumns() && null != taskDescription.getTdTargetTableName() ) {
			try {
				int effectedNum = taskDescriptionMapper.insertTaskDescription(taskDescription);
				if (effectedNum > 0) {
					jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
					return jsonObject.toJSONString();
				} else {
					jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, "添加任务失败，请检查字段是否错误"));
					return jsonObject.toJSONString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
				return jsonObject.toJSONString();
			}
		} else {
			jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, "检查字段是否为空"));
			return jsonObject.toJSONString();
		}
	}

	/**
	 * 修改任务
	 *
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
	@Transactional
	@Override
	public String modifyTaskDescription(int tdTaskId,int tdSourceId, String tdMode, String tdIncrementColumn, String tdTableName, String tdColumns, String tdTargetTableName, String tdDispatch, String tdRemarks ) {

		jsonObject = new JSONObject();
		int tdJobjsonstate = 0 ;
		TaskDescription taskDescription = new TaskDescription( tdTaskId  ,tdSourceId,  tdMode,  tdIncrementColumn,  tdTableName,  tdColumns,  tdTargetTableName,  tdDispatch,  tdRemarks,tdJobjsonstate);
		if (taskDescription.getTdTaskId() != 0 && 0 != taskDescription.getTdSourceId() &&
				null != taskDescription.getTdMode() && null != taskDescription.getTdTableName() &&
				null != taskDescription.getTdColumns() && null != taskDescription.getTdTargetTableName() ) {
			try {
				int effectedNum = taskDescriptionMapper.updateTaskDescription(taskDescription);
				if (effectedNum > 0) {
					jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
					return jsonObject.toJSONString();
				} else {
					jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, "更新失败，请检查字段是否错误"));
					return jsonObject.toJSONString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
				return jsonObject.toJSONString();
			}
		} else {
			jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, "检查字段是否为空"));
			return jsonObject.toJSONString();
		}
	}

	/**
	 * 删除任务
	 *
	 * @param tdTaskId
	 * @return
	 */
	@Transactional
	@Override
	public String deleteTaskDescription(int tdTaskId) {

		jsonObject = new JSONObject();
		int tdJobjsonstate = 2 ;
		if (tdTaskId > 0) {
			try {
				// 删除任务
				int effectedNum = taskDescriptionMapper.deleteTaskDescription(tdTaskId);
				if (effectedNum > 0 ) {
					jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
					taskDescriptionMapper.updateJobjsonstate(tdTaskId,tdJobjsonstate);
					return jsonObject.toJSONString();
				} else {
					jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, "删除失败，请确认taskId是否正确"));
					return jsonObject.toJSONString();
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
				return jsonObject.toJSONString();
			}
		} else {
			jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, "删除失败，检查taskId是否正确"));
			return jsonObject.toJSONString();
		}
	}

	/**
	 * 通过tdTaskId获取任务执行进度
	 *
	 * @param tdTaskId
	 * @return
	 */
	@Override
	public String queryTaskProgressRateById(int tdTaskId) {

		jsonObject = new JSONObject();
		TaskProgressRate taskProgressRate ;
		try{
			taskProgressRate = taskDescriptionMapper.queryTaskProgressRateById(tdTaskId);
			if(taskProgressRate != null){
				jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, Status.stateEnmu.SUCCESS.msg));
				jsonObject.put("data",taskProgressRate);
				return jsonObject.toJSONString();
			}else{
				jsonObject.put("state", new Status(Status.stateEnmu.SUCCESS.code, "数据为空,请确认taskId是否存在"));
				return jsonObject.toJSONString();
			}
		}catch (Exception e){
			e.printStackTrace();
			jsonObject.put("state", new Status(Status.stateEnmu.FAILURE.code, e.getMessage()));
			return jsonObject.toJSONString();
		}

	}
}
