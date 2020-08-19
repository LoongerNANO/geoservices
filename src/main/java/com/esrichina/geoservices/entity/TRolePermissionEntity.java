package com.esrichina.geoservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * <ul>
 * <li>table name:  t_role_permission</li>
 * <li>table comment:  功能表</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-19 09:44:25</li>
 * </ul>
 */ 
@Data
@TableName("t_role_permission")
public class TRolePermissionEntity {

	/*日志唯一标识 */
	private String id;
	/*角色标识 */
	private String role;
	/*权限标识 */
	private String permission;

}

