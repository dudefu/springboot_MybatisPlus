package com.xinyi.xinfo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.xinyi.xinfo.contant.Constant;
import com.xinyi.xinfo.domain.model.DataSource;
import com.xinyi.xinfo.domain.model.TaskDescription;
import com.xinyi.xinfo.domain.repository.DataSourceMapper;
import com.xinyi.xinfo.domain.repository.TaskDescriptionMapper;
import com.xinyi.xinfo.domain.service.DataSourceService;
import com.xinyi.xinfo.domain.service.TaskDescriptionService;
import com.xinyi.xinfo.util.CreateJobJson;
import com.xinyi.xinfo.util.SaveAsJsonFileWriter;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@Api(value = "数据导入任务描述相关操作接口", description = "数据导入任务描述相关操作接口")
@RestController
@RequestMapping("/taskDescription")
public class TaskDescriptionController extends BaseController{

	@Autowired
	private TaskDescriptionService taskDescriptionService ;

	@Autowired
	private DataSourceMapper dataSourceMapper;

	@Autowired
	private TaskDescriptionMapper taskDescriptionMapper;

	private static FastDateFormat fdfWithBar = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	private static FastDateFormat fdfWithNoBar = FastDateFormat.getInstance("yyyyMMddHHmmss");

	/**
	 * 数据导入任务描述列表查询接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryTaskDescriptionList", method = RequestMethod.POST)
	private String getTaskDescriptionList() {
		// 获取区域列表
		 return taskDescriptionService.getTaskDescriptionList();

	}

	/**
	 * 数据导入任务描述详情查询接口
	 *
	 * @param tdTaskId
	 * @return
	 */
	@RequestMapping(value = "/queryTaskDescriptionById", method = RequestMethod.POST)
	private String getTaskDescriptionById(@ApiParam(name = "tdTaskId", value = "任务ID", required = true) @RequestParam(required = true) int tdTaskId) {

		// 获取区域列表
		return taskDescriptionService.getTaskDescriptionById(tdTaskId);


	}

	/**
	 * 	数据导入任务描述生成接口
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
	 *
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/createTaskDescription", method = RequestMethod.POST ,consumes = "application/json")
	private String addTaskDescription(
			@ApiParam(name = "tdTaskId", value = "任务描述id", required = true) @RequestParam(required = true) int tdTaskId,
			@ApiParam(name = "tdSourceId", value = "数据源Id", required = true) @RequestParam(required = true) int tdSourceId,
			@ApiParam(name = "tdMode", value = "增量（Increment）/全量（whole）", required = true) @RequestParam(required = true) String tdMode,
			@ApiParam(name = "tdIncrementColumn", value = "增量导入时标识字段名称", required = false) @RequestParam(required = false) String tdIncrementColumn,
			@ApiParam(name = "tdTableName", value = "创建某个表相同的结构", required = true) @RequestParam(required = true) String tdTableName,
			@ApiParam(name = "tdColumns", value = "同步字段，多个字段用逗号分隔", required = true) @RequestParam(required = true) String tdColumns,
			@ApiParam(name = "tdTargetTableName", value = "同步数据到GP哪张表中", required = true) @RequestParam(required = true) String tdTargetTableName,
			@ApiParam(name = "tdDispatch", value = "调度方式:使用crontab 描述方式,一次全量导入则为空", required = false) @RequestParam(required = false) String tdDispatch,
			@ApiParam(name = "tdRemarks", value = "备注", required = false) @RequestParam(required = false) String tdRemarks
	)throws JsonParseException, JsonMappingException, IOException {

		String jobCreateTime;
		String tdJobjsonFilename = null ;
		int tdJobjsonstate = 0 ;
		try{
			Long currentTimeMillis = System.currentTimeMillis();
			String currentTime = DateFormatUtils.format(currentTimeMillis,"yyyy-MM-dd HH:mm:ss");
			jobCreateTime = fdfWithNoBar.format(fdfWithBar.parse(currentTime));
			tdJobjsonFilename = "Job-"+jobCreateTime+"-"+tdTaskId+".json" ;
		}catch (Exception e){
			e.printStackTrace();
		}
		// 添加任务，存入数据库
		String isSuccess = taskDescriptionService.addTaskDescription( tdTaskId,  tdSourceId,  tdMode,  tdIncrementColumn,  tdTableName,  tdColumns,  tdTargetTableName,  tdDispatch,  tdRemarks,tdJobjsonFilename,tdJobjsonstate);

		//生成jobJson文件
		DataSource dataSource = dataSourceMapper.queryDataSourceById(String.valueOf(tdSourceId));
		TaskDescription taskDescription = taskDescriptionMapper.queryTaskDescriptionById(tdTaskId);
		String createJobJson = CreateJobJson.createJobJson(dataSource,taskDescription);
		JSONObject jsonObject = JSON.parseObject(isSuccess);
		String state = jsonObject.getString("state");
		jsonObject = JSON.parseObject(state);
		String stateSub = jsonObject.getString("state");
		if("10200".equals(stateSub)){
			SaveAsJsonFileWriter.saveAsFileWriter(createJobJson,tdJobjsonFilename);
		}

		//修改定时时间
		Constant.cronTime = tdDispatch;

		return isSuccess;
	}

	/**
	 *  数据导入任务描述修改接口
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
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@RequestMapping(value = "/updateTaskDescription", method = RequestMethod.POST)
	private String modifytaskDescription(
			@ApiParam(name = "tdTaskId", value = "任务描述id", required = true) @RequestParam(required = true) int tdTaskId,
			@ApiParam(name = "tdSourceId", value = "数据源Id", required = true) @RequestParam(required = true) int tdSourceId,
			@ApiParam(name = "tdMode", value = "增量（Increment）/全量（whole）", required = true) @RequestParam(required = true) String tdMode,
			@ApiParam(name = "tdIncrementColumn", value = "增量导入时标识字段名称", required = false) @RequestParam(required = false) String tdIncrementColumn,
			@ApiParam(name = "tdTableName", value = "创建某个表相同的结构", required = true) @RequestParam(required = true) String tdTableName,
			@ApiParam(name = "tdColumns", value = "同步字段，多个字段用逗号分隔", required = true) @RequestParam(required = true) String tdColumns,
			@ApiParam(name = "tdTargetTableName", value = "同步数据到GP哪张表中", required = true) @RequestParam(required = true) String tdTargetTableName,
			@ApiParam(name = "tdDispatch", value = "调度方式:使用crontab 描述方式,一次全量导入则为空", required = false) @RequestParam(required = false) String tdDispatch,
			@ApiParam(name = "tdRemarks", value = "备注", required = false) @RequestParam(required = false) String tdRemarks
	)
			throws JsonParseException, JsonMappingException, IOException {

		// 修改任务
		return taskDescriptionService.modifyTaskDescription(tdTaskId, tdSourceId,  tdMode,  tdIncrementColumn,  tdTableName,  tdColumns,  tdTargetTableName,  tdDispatch,  tdRemarks);

	}

	/**
	 * 数据导入任务描述删除接口
	 * @param tdTaskId
	 * @return
	 */
	@RequestMapping(value = "/deleteTaskDescriptionById", method = RequestMethod.POST)
	private String removeTaskDescription(
			@ApiParam(name = "tdTaskId", value = "任务ID", required = true) @RequestParam(required = true)Integer tdTaskId) {

		// 删除任务
 		return taskDescriptionService.deleteTaskDescription(tdTaskId);

	}


	/**
	 * 数据导入任务执行进度查询
	 *
	 * @param tdTaskId
	 * @return
	 */
	@RequestMapping(value = "/getTaskProgressRateById", method = RequestMethod.POST)
	private String queryTaskProgressRateById(
			@ApiParam(name = "tdTaskId", value = "任务ID", required = true) @RequestParam(required = true)Integer tdTaskId) {
		// 获取区域列表
		return taskDescriptionService.queryTaskProgressRateById(tdTaskId);

	}

}
