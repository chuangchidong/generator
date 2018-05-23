package com.generator.utils;

import java.util.List;

/**
 * @author zhangzhidong
 * @date 2018/5/20
 */
public class ModuleVm {

    /**
     * 模块ID
     */
    Integer moduleId;
    /**
     * 模块名称
     */
    String moduleName;
    /**
     * api列表
     */
    List<ApiVm> apiVmList;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<ApiVm> getApiVmList() {
        return apiVmList;
    }

    public void setApiVmList(List<ApiVm> apiVmList) {
        this.apiVmList = apiVmList;
    }
}
