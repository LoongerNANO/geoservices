package com.esrichina.geoservices.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.esrichina.geoservices.annotation.DictAnnotation;
import lombok.Data;
import java.util.Date;
/**
 * <ul>
 * <li>table name:  t_organization</li>
 * <li>table comment:  组织机构表</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-19 09:44:25</li>
 * </ul>
 */ 
@Data
@TableName("t_organization")
public class TOrganizationEntity {

	/*日志唯一标识 */
	private String id;
	/*父级节点 */
	private String organization_parent;
	/*机构编码 */
	private String organization_code;
	/*机构名称 */
	private String organization_name;
	/*机构简称 */
	private String organization_short_name;
	/*机构类型 */
	private String sys_dic_organization_type;
	/*机构类型名称 */
	@TableField(exist = false)
	@DictAnnotation(value= "sys_dic_organization_type" , refField ="sys_dic_organization_type")
	private String sys_dic_organization_type_name;
	/*机构简介 */
	private String organization_remark;
	/*节点排序 */
	private Integer organization_sort;
	/*创建人 */
	private String organization_creater;
	/*创建时间 */
	private Date organization_created;
	/*最新更新人 */
	private String organization_modifier;
	/*最新更新时间 */
	private Date organization_modified;
	/*机构组织节点类型 */
	private String sys_dic_organization_node;
	/*机构组织节点类型名称 */
	@TableField(exist = false)
	@DictAnnotation(value= "sys_dic_organization_node" , refField ="sys_dic_organization_node")
	private String sys_dic_organization_node_name;

}

