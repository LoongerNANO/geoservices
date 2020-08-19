package com.esrichina.geoservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * <ul>
 * <li>table name:  t_role</li>
 * <li>table comment:  角色表</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-19 09:44:25</li>
 * </ul>
 */ 
@Data
@TableName("t_role")
public class TRoleEntity {

	/*日志唯一标识 */
	private String id;
	/*角色名称 */
	private String role;

}

