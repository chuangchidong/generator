package com.generator.service;

import com.generator.entity.ModuleEntity;

import java.util.List;
import java.util.Map;

/**
 * 模块信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
public interface ModuleService {
	
	ModuleEntity queryObject(Integer id);
	
	List<ModuleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ModuleEntity module);
	
	void update(ModuleEntity module);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
