package com.generator.utils;

import com.generator.entity.ApiEntity;
import com.generator.entity.ModuleEntity;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhangzhidong
 * @date 2018/5/29
 */
public class ApiGenUtils {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("templates/markdown/document.md.vm");
        return templates;
    }


    public static void generatorCode(String projectName, List<ModuleEntity> moduleList, List<ApiEntity> apiList, ZipOutputStream zip) {

        Map<String, Object> map = new HashMap<>();
        map.put("hashSymbol1", "#");
        map.put("hashSymbol2", "##");
        map.put("hashSymbol3", "###");
        map.put("hashSymbol6", "######");
        map.put("interval", "  ");

        map.put("projectName",projectName);
        map.put("apiList",apiList);

        VelocityContext context = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8" );
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, projectName, "1.0")));
                IOUtils.write(sw.toString(), zip, "UTF-8" );
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染文档模板失败：" , e);
            }
        }
    }




    public static String getFileName(String template, String projectName, String version){
        String packagePath = "ZDocument";

        if (template.contains("templates/markdown/document.md.vm" )) {
            return packagePath + File.separator + projectName + "_"+version+".md";
        }

        return null;
    }
}
