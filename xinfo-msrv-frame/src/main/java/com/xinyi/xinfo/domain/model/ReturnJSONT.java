/**
 * 
 */
package com.xinyi.xinfo.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: RespJSON
 * @Description: TODO
 * @author 聂野
 * @date 创建时间：2019年5月9日 上午11:19:02
 * @version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "接口返回结果对象", description = "接口返回结果")
public class ReturnJSONT<T> {
	
	@ApiModelProperty(value = "状态码，是否成功")
	private Integer code = 0;

	@ApiModelProperty(value = "描述")
	private String msg = "";
	
	@ApiModelProperty(value = "请求路径")
	private String cmd = "";

	@ApiModelProperty(value = "业务数据")
	private T data = null;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

//	public ReturnJSONT(Integer code, String cmd, T data) {
//		super();
//		this.code = code;
//		this.cmd = cmd;
//		this.data = data;
//	}
//
//	public ReturnJSONT(Integer code, String cmd) {
//		super();
//		this.code = code;
//		this.cmd = cmd;
//	}
//
//	public ReturnJSONT(Integer code) {
//		super();
//		this.code = code;
//	}
//
//	public static <T> ReturnJSONT<String> success() {
//		return new ReturnJSONT<String>(CodeMsg.C200, "请求成功", "");
//	}
//
//	public static <T> ReturnJSONT<T> success(String cmd, T result) {
//		return new ReturnJSONT<T>(CodeMsg.C200, cmd, result);
//	}
//
//	public static <T> ReturnJSONT<T> success(T result) {
//		return new ReturnJSONT<T>(CodeMsg.C200, "请求成功", result);
//	}
//
//	public static <T> ReturnJSONT<String> success(String cmd) {
//		return new ReturnJSONT<String>(CodeMsg.C200, cmd, "");
//	}
//
//	public static <T> ReturnJSONT<String> fail() {
//		return new ReturnJSONT<String>(CodeMsg.C500, "请求失败", "");
//	}
//
//	public static <T> ReturnJSONT<T> fail(String cmd, T result) {
//		return new ReturnJSONT<T>(CodeMsg.C500, cmd, result);
//	}
//
//	public static <T> ReturnJSONT<String> fail(String cmd) {
//		return new ReturnJSONT<String>(CodeMsg.C500, cmd, "");
//	}
//
//	public static <T> ReturnJSONT<T> fail(T result) {
//		return new ReturnJSONT<T>(CodeMsg.C500, "请求失败", result);
//	}

	

}
