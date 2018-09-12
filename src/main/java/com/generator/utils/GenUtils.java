package com.generator.utils;

import com.alibaba.fastjson.JSONObject;
import com.generator.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author
 *
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {


    private static String DOC_TEMPLATE = "templates/markdown/document.md.vm";
    private static String CONTROLLER_TEMPLATE = "templates/markdown/Controller.java.vm";
    private static String REQUEST_FORM_TEMPLATE = "templates/markdown/Form.java.vm";
    private static String RESPONSE_RESULT_TEMPLATE = "templates/markdown/Result.java.vm";

    /**
     * 原有mybatis模板
     * @return
     */
    public static List<String> getMyBaitsTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("templates/Entity.java.vm");
        templates.add("templates/Dao.java.vm");
        templates.add("templates/Dao.xml.vm");
        templates.add("templates/Service.java.vm");
        templates.add("templates/ServiceImpl.java.vm");
        templates.add("templates/Controller.java.vm");
        templates.add("templates/list.html.vm");
        templates.add("templates/list.js.vm");
        templates.add("templates/menu.sql.vm");

        return templates;
    }

    /**
     * jpa文件模板
     * @return
     */
    public static List<String> getJpaTemplates() {

        List<String> templates = new ArrayList<>();
        templates.add("templates/jpa/Entity.java.vm");
        templates.add("templates/jpa/Repository.java.vm");

        return templates;
    }

    /**
     * 接口为生成文档和controller模板
     * @return
     */
    public static List<String> getMarkdownTemplates() {

        List<String> templates = new ArrayList<>();
        templates.add(DOC_TEMPLATE);
        templates.add(CONTROLLER_TEMPLATE);
        templates.add(REQUEST_FORM_TEMPLATE);
        templates.add(RESPONSE_RESULT_TEMPLATE);

        return templates;
    }


    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip) {
        //配置信息
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName" ));
        tableEntity.setComments(table.get("tableComment" ));
        //表名转换成Java类名
        String className = GenStringUtils.tableToJava(tableEntity.getTableName(), config.getString("tablePrefix" ));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<ColumnEntity> columnsList = new ArrayList<>();
        for(Map<String, String> column : columns){
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName" ));
            columnEntity.setDataType(column.get("dataType" ));
            columnEntity.setComments(column.get("columnComment" ));
            columnEntity.setExtra(column.get("extra" ));
            columnEntity.setLength(column.get("length"));
            columnEntity.setNullable(column.get("nullable"));
            columnEntity.setJpaColumnDefinition(column.get("jpaColumnDefinition" ));

            //列名转换成Java属性名
            String attrName = GenStringUtils.columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType" );
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && "BigDecimal".equals(attrType)) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey" )) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columnsList.add(columnEntity);
        }
        tableEntity.setColumns(columnsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }


        String mainPath = config.getString("mainPath" );
        mainPath = StringUtils.isBlank(mainPath) ? "com.generator" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>(1 << 4);
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package" ));
        map.put("moduleName", config.getString("moduleName" ));
        map.put("author", config.getString("author" ));
        map.put("email", config.getString("email" ));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

        //获取模板列表
        List<String> templates = getMyBaitsTemplates();
        for (String template : templates) {

            String fileName = getFileName(template, tableEntity.getClassName(), config.getString("package" ), config.getString("moduleName" ));
            if (StringUtils.isBlank(fileName)) {
                continue;
            }
            generator(template, map, fileName, zip);
        }
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties" );
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains("Entity.java.vm" )) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Dao.java.vm" )) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Repository.java.vm" )) {
            return packagePath + "repository" + File.separator + className + "Repository.java";
        }

        if (template.contains("Service.java.vm" )) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm" )) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm" )) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Dao.xml.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
        }

        if (template.contains("list.html.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "views" + File.separator
                    + "modules" + File.separator + moduleName + File.separator + className.toLowerCase() + ".html";
        }

        if (template.contains("list.js.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
                    + "modules" + File.separator + moduleName + File.separator + className.toLowerCase() + ".js";
        }

        if (template.contains("menu.sql.vm" )) {
            return className.toLowerCase() + "_menu.sql";
        }

        if (template.contains(REQUEST_FORM_TEMPLATE)) {
            return packagePath + "form" + File.separator + className + "Form.java";
        }
        if (template.contains(RESPONSE_RESULT_TEMPLATE)) {
            return packagePath + "result" + File.separator + className + "Result.java";
        }
        if (template.contains(CONTROLLER_TEMPLATE)) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        return null;
    }


    /**
     * 根据接口信息生成文档和代码
     *
     * @param projectName
     * @param moduleList
     * @param apiList
     * @param zip
     */
    public static void generatorDoc(String projectName, List<ModuleEntity> moduleList, List<ApiEntity> apiList, ZipOutputStream zip) {

        // 生成doc文档
        generatorDocFile(projectName, apiList, zip);
        // 生成Controller文件
        generatorController(moduleList, apiList, zip);
        // 生成出参入参文件
        generatorParam(apiList, zip);

    }

    /**
     * 生成接口出参入参文件
     * @param apiList
     * @param zip
     */
    private static void generatorParam(List<ApiEntity> apiList, ZipOutputStream zip) {
        for (ApiEntity apiEntity : apiList) {

            Configuration config = getConfig();
            //类名(第一个字母小写)，如：sys_user => sysUser
            String classname =  WordUtils.capitalizeFully(apiEntity.getUrl(), new char[]{'/'}).replace("/", "" );
            //类名(第一个字母大写)，如：sys_user => SysUser
            String className = GenStringUtils.toUpperCaseFirstOne(classname);

            // 请求参数类
            if (CollectionUtils.isNotEmpty(apiEntity.getRequestList())) {
                TableEntity tableEntity = new TableEntity();
                List<ColumnEntity> columnsList = new ArrayList<>();

                tableEntity.setClassName(className);
                tableEntity.setClassname(classname);
                tableEntity.setComments(apiEntity.getName());
                for (RequestEntity requestEntity : apiEntity.getRequestList()) {
                    ColumnEntity columnEntity = new ColumnEntity();

                    columnEntity.setDataType(requestEntity.getType());
                    columnEntity.setColumnName(requestEntity.getField());
                    columnEntity.setComments(requestEntity.getDesc());
                    columnEntity.setAttrType(requestEntity.getType());

                    //列名转换成Java属性名
                    String attrName = GenStringUtils.toUpperCaseFirstOne(requestEntity.getField());
                    columnEntity.setAttrName(attrName);
                    columnEntity.setAttrname(GenStringUtils.toLowerCaseFirstOne(attrName));
                    // 1能 0.不能
                    columnEntity.setNullable(requestEntity.getIsNullable().toString());

                    columnsList.add(columnEntity);
                }
                tableEntity.setColumns(columnsList);

                Map<String, Object> map = new HashMap<>(1 << 4);

                map.put("package", config.getString("package" ));
                map.put("moduleName", config.getString("moduleName" ));
                map.put("author", config.getString("author" ));
                map.put("email", config.getString("email" ));
                map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
                map.put("classname", classname);
                map.put("className", className);
                map.put("comments", apiEntity.getName());
                map.put("columns", tableEntity.getColumns());

                String fileName = getFileName(REQUEST_FORM_TEMPLATE, className, config.getString("package"), config.getString("moduleName"));
                generator(REQUEST_FORM_TEMPLATE, map, fileName, zip);
            }

            // 输出结果类
            if (CollectionUtils.isNotEmpty(apiEntity.getResponseList())) {
                TableEntity tableEntity = new TableEntity();
                List<ColumnEntity> columnsList = new ArrayList<>();

                tableEntity.setClassName(className);
                tableEntity.setClassname(classname);
                tableEntity.setComments(apiEntity.getName());
                for (ResponseEntity responseEntity : apiEntity.getResponseList()) {
                    ColumnEntity columnEntity = new ColumnEntity();

                    columnEntity.setDataType(responseEntity.getType());
                    columnEntity.setColumnName(responseEntity.getField());
                    columnEntity.setComments(responseEntity.getDesc());
                    columnEntity.setAttrType(responseEntity.getType());

                    //列名转换成Java属性名
                    String attrName = GenStringUtils.toUpperCaseFirstOne(responseEntity.getField());
                    columnEntity.setAttrName(attrName);
                    columnEntity.setAttrname(GenStringUtils.toLowerCaseFirstOne(attrName));

                    columnsList.add(columnEntity);
                }
                tableEntity.setColumns(columnsList);

                Map<String, Object> map = new HashMap<>(1 << 4);

                map.put("package", config.getString("package" ));
                map.put("moduleName", config.getString("moduleName" ));
                map.put("author", config.getString("author" ));
                map.put("email", config.getString("email" ));
                map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
                map.put("classname", classname);
                map.put("className", className);
                map.put("comments", apiEntity.getName());
                map.put("columns", tableEntity.getColumns());

                String fileName = getFileName(RESPONSE_RESULT_TEMPLATE, className, config.getString("package"), config.getString("moduleName"));
                generator(RESPONSE_RESULT_TEMPLATE, map, fileName, zip);
            }
        }
    }

    private static ApiAbstract conversionApi(ApiEntity apiEntity) {
        ApiAbstract api = new ApiAbstract();

        api.setUrl(apiEntity.getUrl());
        api.setMethodDesc(apiEntity.getName());

        String methodType = apiEntity.getMethod().toUpperCase();
        api.setMethodType(methodType);

        if ("post".equalsIgnoreCase(methodType)) {
            api.setRequestAnnotation("@RequestBody");
        } else if ("get".equalsIgnoreCase(methodType)) {
            api.setRequestAnnotation("@ModelAttribute");
        } else {
            api.setRequestAnnotation("");
        }

        // 方法名称
        String methodName =  WordUtils.capitalizeFully(apiEntity.getUrl(), new char[]{'/'}).replace("/", "" );
        methodName = GenStringUtils.toLowerCaseFirstOne(methodName);
        api.setMethodName(methodName);

        String methodUpperCase = GenStringUtils.toUpperCaseFirstOne(methodName);
        api.setMethodUpperCase(methodUpperCase);


        return api;
    }

    /**
     * 生成md文档文件
     * @param projectName
     * @param apiList
     * @param zip
     */
    public static void generatorDocFile(String projectName,List<ApiEntity> apiList, ZipOutputStream zip){
        // 文档文件名称
        String fileName = getDocFileName(DOC_TEMPLATE, projectName, "1.0");
        Map<String, Object> map = new HashMap<>(1 << 4);
        map.put("hashSymbol1", "#");
        map.put("hashSymbol2", "##");
        map.put("hashSymbol3", "###");
        map.put("hashSymbol6", "######");
        map.put("interval", "  ");

        map.put("projectName", projectName);
        map.put("apiList", apiList);
        generator(DOC_TEMPLATE, map, fileName, zip);
    }

    /**
     * 生成文件流
     * @param template 模板
     * @param map 数据
     * @param zip  文件流
     */
    public static void generator(String template, Map<String, Object> map,String fileName, ZipOutputStream zip) {
        if (StringUtils.isBlank(fileName)) {
            return;
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        VelocityContext context = new VelocityContext(map);
        //渲染模板
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(template, "UTF-8" );
        tpl.merge(context, sw);
        try {
            //添加到zip
            zip.putNextEntry(new ZipEntry(fileName));
            IOUtils.write(sw.toString(), zip, "UTF-8" );
            IOUtils.closeQuietly(sw);
            zip.closeEntry();
        } catch (IOException e) {
            throw new RRException("文件生成失败：" , e);
        }

    }



    /**
     * 根据接口文档生成Controller文件
     * @param apiList
     * @param zip
     */
    public static void generatorController(List<ModuleEntity> moduleList, List<ApiEntity> apiList, ZipOutputStream zip) {

        //配置信息
        Configuration config = getConfig();
        for (ModuleEntity moduleEntity : moduleList) {
            List<ApiAbstract> apiAbstractList = new ArrayList<>();
            ApiAbstract api;
            for (ApiEntity apiEntity : apiList) {
                if (apiEntity.getModuleId().equals(moduleEntity.getId())) {
                    api = conversionApi(apiEntity);
                    apiAbstractList.add(api);
                }
            }

            Map<String, Object> map = new HashMap<>(1 << 4);

            map.put("package", config.getString("package"));
            map.put("moduleName", config.getString("moduleName"));
            map.put("author", config.getString("author"));
            map.put("email", config.getString("email"));
            map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

            // 类文件注释说明
            String classDesc = moduleEntity.getName();
            map.put("comments",classDesc);

            // 类名称
            String className = moduleEntity.getCode();
            className = GenStringUtils.toUpperCaseFirstOne(className);
            map.put("className", className);

            // 接口信息
            map.put("apiList", apiAbstractList);

            String fileName = getFileName(CONTROLLER_TEMPLATE, className, config.getString("package"), config.getString("moduleName"));

            generator(CONTROLLER_TEMPLATE, map, fileName, zip);
        }

    }

    public static String getDocFileName(String template, String projectName, String version){
        String packagePath = "ZDocument";

        if (template.contains(DOC_TEMPLATE)) {
            return packagePath + File.separator + projectName + "_"+version+".md";
        }

        return null;
    }

    public static String responseJson(List<ResponseEntity> responseList) {
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
