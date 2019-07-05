package com.generator.service;

import com.generator.entity.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * 返回结果字段信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
public interface ResponseService {
	
	ResponseEntity queryObject(Integer id);
	
	List<ResponseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ResponseEntity response);
	
	void update(ResponseEntity response);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
