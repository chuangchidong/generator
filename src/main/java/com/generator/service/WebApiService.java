package com.generator.service;

import com.generator.dao.ApiDao;
import com.generator.dao.RequestDao;
import com.generator.dao.ResponseDao;
import com.generator.entity.ApiEntity;
import com.generator.entity.RequestEntity;
import com.generator.entity.ResponseEntity;
import com.generator.form.ApiRequestVo;
import com.generator.form.ApiResponseVo;
import com.generator.form.WebApiSaveForm;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhidong
 * @date 2018/7/16
 */
@Service
public class WebApiService {

    @Autowired
    private ApiDao apiDao;
    @Autowired
    private RequestDao requestDao;
    @Autowired
    private ResponseDao responseDao;

    public Object apiSave(WebApiSaveForm form) {
        ApiEntity apiEntity = new ApiEntity();
        apiEntity.setMethod(form.getMethod());
        apiEntity.setName(form.getName());
        apiEntity.setModuleId(form.getModuleId());
        apiEntity.setProjectId(form.getProjectId());
        apiEntity.setUrl(form.getUrl());
        apiEntity.setId(form.getApiId());
        if (form.getApiId() == null) {
            apiDao.save(apiEntity);
        } else {
            apiDao.update(apiEntity);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("api_id",apiEntity.getId());
        requestDao.deleteMap(map);
        RequestEntity requestEntity;
        if (CollectionUtils.isNotEmpty(form.getRequestList())) {
            for (ApiRequestVo vo : form.getRequestList()) {
                requestEntity = new RequestEntity();
                requestEntity.setModuleId(form.getModuleId());
                requestEntity.setProjectId(form.getProjectId());
                requestEntity.setApiId(apiEntity.getId());
                requestEntity.setField(vo.getField());
                requestEntity.setIsNullable(vo.getIsNullable());
                requestEntity.setType(vo.getType());
                requestEntity.setDesc(vo.getDesc());
                requestDao.save(requestEntity);
            }
        }
        ResponseEntity responseEntity;
        responseDao.deleteMap(map);
        if (CollectionUtils.isNotEmpty(form.getResponseList())) {
            for (ApiResponseVo vo : form.getResponseList()) {
                responseEntity = new ResponseEntity();
                responseEntity.setModuleId(form.getModuleId());
                responseEntity.setProjectId(form.getProjectId());
                responseEntity.setApiId(apiEntity.getId());
                responseEntity.setField(vo.getField());
                responseEntity.setMock(vo.getMock());
                responseEntity.setType(vo.getType());
                responseEntity.setDesc(vo.getDesc());
                responseDao.save(responseEntity);
            }
        }

        return true;
    }
}
