package com.generator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.generator.dao.ApiDao;
import com.generator.entity.ApiEntity;
import com.generator.service.ApiService;

@Service("apiService")
public class ApiServiceImpl implements ApiService {
	@Autowired
	private ApiDao apiDao;
	
	@Override
	public ApiEntity queryObject(Integer id){
		return apiDao.queryObject(id);
	}
	
	@Override
	public List<ApiEntity> queryList(Map<String, Object> map){
		return apiDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return apiDao.queryTotal(map);
	}
	
	@Override
	public void save(ApiEntity api){
		apiDao.save(api);
	}
	
	@Override
	public void update(ApiEntity api){
		apiDao.update(api);
	}
	
	@Override
	public void delete(Integer id){
		apiDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		apiDao.deleteBatch(ids);
	}
	
}
