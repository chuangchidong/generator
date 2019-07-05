package com.generator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.generator.dao.RequestDao;
import com.generator.entity.RequestEntity;
import com.generator.service.RequestService;

@Service("requestService")
public class RequestServiceImpl implements RequestService {
	@Autowired
	private RequestDao requestDao;
	
	@Override
	public RequestEntity queryObject(Integer id){
		return requestDao.queryObject(id);
	}
	
	@Override
	public List<RequestEntity> queryList(Map<String, Object> map){
		return requestDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return requestDao.queryTotal(map);
	}
	
	@Override
	public void save(RequestEntity request){
		requestDao.save(request);
	}
	
	@Override
	public void update(RequestEntity request){
		requestDao.update(request);
	}
	
	@Override
	public void delete(Integer id){
		requestDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		requestDao.deleteBatch(ids);
	}
	
}
