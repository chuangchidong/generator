package com.generator.entity;

import java.io.Serializable;

/**
 * 模块信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
public class ModuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer id;
	//项目ID
	private Integer projectId;
	//名称
	private String name;

	/**
	 * 名称
	 */
	private String code;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
