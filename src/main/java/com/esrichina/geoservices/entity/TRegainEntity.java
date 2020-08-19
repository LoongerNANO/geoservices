package com.esrichina.geoservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * <ul>
 * <li>table name:  t_regain</li>
 * <li>table comment:  行政区划表</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-19 09:44:25</li>
 * </ul>
 */ 
@Data
@TableName("t_regain")
public class TRegainEntity {

	/*日志唯一标识 */
	private String id;
	/*功能标识 */
	private String parent_id;
	/*行政区划名称 */
	private String name;
	/*行政区划编码 */
	private String code;

}

