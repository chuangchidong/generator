package com.generator.entity;

import java.io.Serializable;

/**
 * 返回结果字段信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
public class ResponseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer id;
	//项目ID
	private Integer projectId;
	//项目模块ID
	private Integer moduleId;
	//API接口ID
	private Integer apiId;
	//上一级字段ID
	private Integer parentId;
	//字段
	private String field;
	//类型
	private String type;
	//描述
	private String desc;
	/**
	 * mock值
	 */
	private String mock;
	/**
	 * 设置：主键ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：项目ID
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目ID
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * 设置：项目模块ID
	 */
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * 获取：项目模块ID
	 */
	public Integer getModuleId() {
		return moduleId;
	}
	/**
	 * 设置：API接口ID
	 */
	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}
	/**
	 * 获取：API接口ID
	 */
	public Integer getApiId() {
		return apiId;
	}
	/**
	 * 设置：上一级字段ID
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上一级字段ID
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：字段
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * 获取：字段
	 */
	public String getField() {
		return field;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：描述
	 */
	public String getDesc() {
		return desc;
	}

	public String getMock() {
		return mock;
	}

	public void setMock(String mock) {
		this.mock = mock;
	}
}
