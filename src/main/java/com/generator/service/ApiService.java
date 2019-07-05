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

	/**
	 * 查询对象
	 * @param id API id
	 * @return API对象
     */
	ApiEntity queryObject(Integer id);

	/**
	 * 查询列表
	 * @param map
	 * @return
     */
	List<ApiEntity> queryList(Map<String, Object> map);

	/**
	 * 查询总数
	 * @param map
	 * @return
     */
	int queryTotal(Map<String, Object> map);

	/**
	 * 保存对象
	 * @param api
     */
	void save(ApiEntity api);

	/**
	 * 更新对象
	 * @param api
     */
	void update(ApiEntity api);

	/**
	 * 根据ID删除
	 * @param id
     */
	void delete(Integer id);

	/**
	 * 根据ID数组批量删除
	 * @param ids
     */
	void deleteBatch(Integer[] ids);
}
