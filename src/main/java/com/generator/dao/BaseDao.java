package com.generator.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017年9月18日 上午9:31:36
 */
public interface BaseDao<T> {
	/**
	 * 保存对象
	 * @param t
     */
	void save(T t);

	/**
	 * 保存对象
	 * @param map
     */
	void save(Map<String, Object> map);

	/**
	 * 批量保存对象
	 * @param list
     */
	void saveBatch(List<T> list);

	/**
	 * 更新对象
	 * @param t
	 * @return
     */
	int update(T t);

	/**
	 * 更新对象
	 * @param map
	 * @return
     */
	int update(Map<String, Object> map);

	/**
	 * 删除对象
	 * @param id
	 * @return
     */
	int delete(Object id);

	/**
	 * 删除对象
	 * @param map
	 * @return
     */
	int delete(Map<String, Object> map);

	/**
	 * 删除对象
	 * @param map
	 * @return
     */
	int deleteMap(Map<String, Object> map);

	/**
	 * 按照ID批量删除对象
	 * @param id
	 * @return
     */
	int deleteBatch(Object[] id);

	/**
	 * 按照ID查询对象
	 * @param id
	 * @return
     */
	T queryObject(Object id);

	/**
	 * 按照条件查询对象列表
	 * @param map
	 * @return
     */
	List<T> queryList(Map<String, Object> map);

	/**
	 * 按照对象属性查询对象列表
	 * @param obj
	 * @return
     */
	List<T> queryList(Object obj);

	/**
	 * 按照字段信息查询数量
	 * @param map
	 * @return
     */
	int queryTotal(Map<String, Object> map);

	/**
	 * 查询总数
	 * @return
     */
	int queryTotal();
}
