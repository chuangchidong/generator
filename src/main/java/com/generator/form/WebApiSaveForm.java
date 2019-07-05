package com.generator.form;

import java.util.List;

/**
 * @author zhangzhidong
 * @date 2018/7/16
 */
public class WebApiSaveForm {

    private Integer projectId;
    private Integer moduleId;
    private Integer apiId;
    private String name;
    private String url;
    private String method;
    private List<ApiRequestVo> requestList;
    private List<ApiResponseVo> responseList;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<ApiRequestVo> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<ApiRequestVo> requestList) {
        this.requestList = requestList;
    }

    public List<ApiResponseVo> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<ApiResponseVo> responseList) {
        this.responseList = responseList;
    }
}
