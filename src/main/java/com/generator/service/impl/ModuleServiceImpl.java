package com.generator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.generator.dao.ModuleDao;
import com.generator.entity.ModuleEntity;
import com.generator.service.ModuleService;



@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleDao moduleDao;
	
	@Override
	public ModuleEntity queryObject(Integer id){
		return moduleDao.queryObject(id);
	}
	
	@Override
	public List<ModuleEntity> queryList(Map<String, Object> map){
		return moduleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return moduleDao.queryTotal(map);
	}
	
	@Override
	public void save(ModuleEntity module){
		moduleDao.save(module);
	}
	
	@Override
	public void update(ModuleEntity module){
		moduleDao.update(module);
	}
	
	@Override
	public void delete(Integer id){
		moduleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		moduleDao.deleteBatch(ids);
	}
	
}
