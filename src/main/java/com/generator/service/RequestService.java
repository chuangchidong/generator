package com.generator.service;

import com.generator.entity.RequestEntity;

import java.util.List;
import java.util.Map;

/**
 * 请求参数信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
public interface RequestService {
	
	RequestEntity queryObject(Integer id);
	
	List<RequestEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RequestEntity request);
	
	void update(RequestEntity request);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
