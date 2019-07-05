package com.generator.dao;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author
 *
 * @date 2016年12月19日 下午3:32:04
 */
public interface SysGeneratorDao {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);

	/**
	 * 根据条件查询总数
	 * @param map
	 * @return
     */
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
}
