package com.generator.service;

import com.generator.entity.ApiEntity;

import java.util.List;
import java.util.Map;

/**
 * 模块信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
public interface ApiService {
	
	ApiEntity queryObject(Integer id);
	
	List<ApiEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ApiEntity api);
	
	void update(ApiEntity api);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
