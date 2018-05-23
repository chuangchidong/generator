package com.generator.utils;

import java.util.List;

/**
 * @author zhangzhidong
 * @date 2018/5/20
 */
public class ProjectVm {

    /**
     * 项目编号
     */
    Integer projectId;
    /**
     * 项目名称
     */
    String projectName;

    /**
     * 对应模板信息列表
     */
    List<ModuleVm> moduleVmList;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ModuleVm> getModuleVmList() {
        return moduleVmList;
    }

    public void setModuleVmList(List<ModuleVm> moduleVmList) {
        this.moduleVmList = moduleVmList;
    }
}
