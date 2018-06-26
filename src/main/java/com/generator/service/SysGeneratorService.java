package com.generator.service;

import com.alibaba.fastjson.JSONObject;
import com.generator.dao.ApiDao;
import com.generator.dao.RequestDao;
import com.generator.dao.ResponseDao;
import com.generator.dao.SysGeneratorDao;
import com.generator.entity.ApiEntity;
import com.generator.entity.RequestEntity;
import com.generator.entity.ResponseEntity;
import com.generator.utils.GenUtils;
import com.generator.utils.JsonFormatTool;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * 
 * @author
 *
 * @date 2016年12月19日 下午3:33:38
 */
@Service
public class SysGeneratorService {
	@Autowired
	private SysGeneratorDao sysGeneratorDao;

	@Autowired
	private ApiDao apiDao;
	@Autowired
	private RequestDao requestDao;
	@Autowired
	private ResponseDao responseDao;

	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		return sysGeneratorDao.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		return sysGeneratorDao.queryTotal(map);
	}

	public Map<String, String> queryTable(String tableName) {
		return sysGeneratorDao.queryTable(tableName);
	}

	public List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorDao.queryColumns(tableName);
	}

	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	public byte[] apiGeneratorCode(List<Integer> apiIds) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		List<ApiEntity> apiList = new ArrayList<>();
		for (Integer apiId : apiIds) {

			ApiEntity apiEntity = apiDao.queryObject(apiId);

			Map<String,Object> param = new HashMap<>();
			param.put("api_id",apiId);

			List<RequestEntity> requestList = requestDao.queryList(param);
			apiEntity.setRequestList(requestList);

			List<ResponseEntity> responseList = responseDao.queryList(param);
			apiEntity.setResponseList(responseList);
			apiEntity.setResponseJson(this.responseJson(responseList));
			apiList.add(apiEntity);
		}
		GenUtils.generatorDoc("测试文档", null, apiList, zip);
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}


	public String responseJson(List<ResponseEntity> responseList) {
		Map<String, Object> resultMap = new LinkedHashMap<>();
		String field;
		Object type;
		for (ResponseEntity entity : responseList) {
			field = entity.getField();
			type = "String".equalsIgnoreCase(entity.getType()) ? entity.getDesc() : 0;
			resultMap.put(field, type);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("message", "SUCCESS");
		map.put("extra", resultMap);
		String json = JSONObject.toJSONString(map);
		return JsonFormatTool.formatJson(json);
	}

}
