package com.esrichina.geoservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * <ul>
 * <li>table name:  t_permission</li>
 * <li>table comment:  权限表</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-19 09:44:25</li>
 * </ul>
 */ 
@Data
@TableName("t_permission")
public class TPermissionEntity {

	/*日志唯一标识 */
	private String id;
	/*权限标识 */
	private String permission;
	/*权限描述 */
	private String description;

}

