package com.generator.dao;

import com.generator.entity.ResponseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 返回结果字段信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
@Mapper
public interface ResponseDao extends BaseDao<ResponseEntity> {
	
}
