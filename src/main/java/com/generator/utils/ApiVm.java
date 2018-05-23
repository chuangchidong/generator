package com.generator.utils;

import java.util.List;

/**
 * @author zhangzhidong
 * @date 2018/5/20
 */
public class ApiVm {

    /**
     * apiId
     */
    Integer apiId;
    /**
     * api名称
     */
    String apiName;

    /**
     * 请求参数
     */
    List<RequestVm> requestVmList;
    /**
     * 返回结果
     */
    List<ResponseVm> responseVmList;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public List<RequestVm> getRequestVmList() {
        return requestVmList;
    }

    public void setRequestVmList(List<RequestVm> requestVmList) {
        this.requestVmList = requestVmList;
    }

    public List<ResponseVm> getResponseVmList() {
        return responseVmList;
    }

    public void setResponseVmList(List<ResponseVm> responseVmList) {
        this.responseVmList = responseVmList;
    }


}
