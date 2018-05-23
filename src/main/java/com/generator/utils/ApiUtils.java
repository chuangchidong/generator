package com.generator.utils;

import com.generator.entity.ProjectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzhidong
 * @date 2018/5/20
 */
public class ApiUtils {

    /**
     * 接口文档相关模板
     * @return
     */
    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();

        templates.add("templates/markdown/document.md.vm");

        return templates;
    }

    /**
     * 项目
     *  -- 模块
     *     -- 接口
     *        --请求参数
     *        --返回参数
     */

    public static void generatorCode(ProjectVm vm) {

    }

}
