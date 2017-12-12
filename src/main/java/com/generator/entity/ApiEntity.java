package com.generator.entity;

import java.io.Serializable;

/**
 * 模块信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
public class ApiEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer id;
	//项目ID
	private Integer projectId;
	//项目模块ID
	private Integer moduleId;
	//名称
	private String name;
	//url路径
	private String url;
	//访问方式
	private String method;

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
	/**
	 * 设置：url路径
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：url路径
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：访问方式
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：访问方式
	 */
	public String getMethod() {
		return method;
	}
}
