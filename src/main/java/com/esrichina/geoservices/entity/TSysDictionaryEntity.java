package com.esrichina.geoservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
/**
 * <ul>
 * <li>table name:  t_sys_dictionary</li>
 * <li>table comment:  系统字典表</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-19 09:44:25</li>
 * </ul>
 */ 
@Data
@TableName("t_sys_dictionary")
public class TSysDictionaryEntity {

	/*字典唯一编码 */
	private String id;
	/*字典类型编码（字典类型表） */
	private String identify;
	/*字典编码（同一类型是唯一） */
	private String code;
	/*字典名称 */
	private String name;
	/*字典排序（同一类型排序） */
	private Integer seq;
	/*字典备注 */
	private String remarks;
	/*创建人 */
	private String creater;
	/*创建日期 */
	private Date created;
	/*修改人 */
	private String updater;
	/*修改日期 */
	private Date updated;

}

