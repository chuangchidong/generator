package com.generator.service;

import com.generator.entity.ProjectEntity;

import java.util.List;
import java.util.Map;

/**
 * 项目信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
public interface ProjectService {
	
	ProjectEntity queryObject(Integer id);
	
	List<ProjectEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ProjectEntity project);
	
	void update(ProjectEntity project);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
