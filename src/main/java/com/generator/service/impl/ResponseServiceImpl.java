package com.generator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.generator.dao.ResponseDao;
import com.generator.entity.ResponseEntity;
import com.generator.service.ResponseService;



@Service("responseService")
public class ResponseServiceImpl implements ResponseService {
	@Autowired
	private ResponseDao responseDao;
	
	@Override
	public ResponseEntity queryObject(Integer id){
		return responseDao.queryObject(id);
	}
	
	@Override
	public List<ResponseEntity> queryList(Map<String, Object> map){
		return responseDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return responseDao.queryTotal(map);
	}
	
	@Override
	public void save(ResponseEntity response){
		responseDao.save(response);
	}
	
	@Override
	public void update(ResponseEntity response){
		responseDao.update(response);
	}
	
	@Override
	public void delete(Integer id){
		responseDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		responseDao.deleteBatch(ids);
	}
	
}
