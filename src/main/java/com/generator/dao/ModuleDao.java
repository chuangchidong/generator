package com.generator.dao;

import com.generator.entity.ModuleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 模块信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
@Mapper
public interface ModuleDao extends BaseDao<ModuleEntity> {

    /**
     * 根据接口Id获取模板信息
     * @param apiIds 接口ID集合
     * @return
     */
	List<ModuleEntity> queryListByApiIds(List<Integer> apiIds);
}
