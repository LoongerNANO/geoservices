package com.esrichina.geoservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.esrichina.geoservices.annotation.DictAnnotation;
import lombok.Data;
import java.util.Date;
/**
 * <ul>
 * <li>table name:  t_sys_log</li>
 * <li>table comment:  系统日志表</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-07-20 10:23:39</li>
 * </ul>
 */ 
@Data
@TableName("t_sys_log")
public class TSysLogEntity {

	/*日志唯一标识 */
	private String id;
	/*请求人账号 */
	private String username;
	/*请求人姓名 */
	private String fullname;
	/*请求地址 */
	private String url;
	/*请求方式 */
	private String method;
	/*客户端IP */
	private String ip;
	/*响应类函数 */
	private String functions;
	/*请求参数 */
	private String parameter;
	/*操作内容描述 */
	private String operation;
	/*响应时间 */
	private String time;
	/*请求时间 */
	private Date ask;
	/*日志类型 */
	private String log_type;
	/*日志类型名称 */
	@TableField(exist = false)
	@DictAnnotation(value= "sys_log_type" , refField ="log_type")
	private String log_type_name;
	/*错误描述 */
	private String error;
	/*客户端浏览器名称 */
	private String browser;
	/*客户端浏览器版本 */
	private String version;
	/*客户端操作系统 */
	private String os;

}

