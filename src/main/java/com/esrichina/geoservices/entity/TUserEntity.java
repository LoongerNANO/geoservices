package com.esrichina.geoservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.esrichina.geoservices.annotation.DictAnnotation;
import lombok.Data;
import java.util.Date;
/**
 * <ul>
 * <li>table name:  t_user</li>
 * <li>table comment:  用户表</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-19 09:44:25</li>
 * </ul>
 */ 
@Data
@TableName("t_user")
public class TUserEntity {

	/*日志唯一标识 */
	private String id;
	/*所属组织机构标识 */
	private String org_id;
	/*所属组织机构名称 */
	private String org_name;
	/*用户名 */
	private String username;
	/*密码（加盐） */
	private String password;
	/*用户姓名 */
	private String name;
	/*用户性别 */
	private String sex;
	/*身份证号 */
	private String card;
	/*电子邮箱 */
	private String email;
	/*手机号 */
	private String mobile;
	/*办公室电话 */
	private String office_phone;
	/*角色标识 */
	private String role;
	/*用户状态 */
	private String sys_dic_user_status;
	/*用户状态名称 */
	@TableField(exist = false)
	@DictAnnotation(value= "sys_dic_user_status" , refField ="sys_dic_user_status")
	private String sys_dic_user_status_name;
	/*创建人 */
	private String user_creater;
	/*创建时间 */
	private Date user_created;
	/*最新更新人 */
	private String user_modifier;
	/*最新更新时间 */
	private Date user_modified;
	/*所属组织机构类型 */
	private String sys_dic_organization_type;
	/*所属组织机构类型名称 */
	@TableField(exist = false)
	@DictAnnotation(value= "sys_dic_organization_type" , refField ="sys_dic_organization_type")
	private String sys_dic_organization_type_name;

}

