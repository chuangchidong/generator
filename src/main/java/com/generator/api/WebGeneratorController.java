package com.generator.api;

import com.alibaba.fastjson.JSON;
import com.generator.entity.*;
import com.generator.service.*;
import com.generator.utils.PageUtils;
import com.generator.utils.Query;
import com.generator.utils.R;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzhidong
 * @date 2018/5/31
 */
@RestController
public class WebGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    @Autowired
    private ProjectService projectService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/web/mysql/list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        int total = sysGeneratorService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("data", pageUtil);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/web/mysql/code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{};
        String tables = request.getParameter("tables");
        if (tables != null) {
            tableNames = JSON.parseArray(tables).toArray(tableNames);
        }else if (request.getParameterValues("tables[]") !=null) {
            tableNames = request.getParameterValues("tables[]");
        }

        byte[] data = sysGeneratorService.generatorCode(tableNames);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generator.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }


    /**
     * 项目列表
     * @param params
     * @return
     */
    @RequestMapping("/web/project/list")
    public R projectList(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);

        List<ProjectEntity> projectList = projectService.queryList(query);
        int total = projectService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(projectList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", pageUtil);
    }

    @Autowired
    private ModuleService moduleService;

    /**
     * 列表
     */
    @RequestMapping("/web/module/list")
    public R moduleList(@RequestParam Map<String, Object> params){
        String projectIdList = params.get("projectIdList") == null ? null : params.get("projectIdList").toString();
        if (StringUtils.isNotBlank(projectIdList)) {
            String[] arr = projectIdList.split(",");
            List<Integer> idList = new ArrayList<>();
            for (String i : arr) {
                if (StringUtils.isNotBlank(i)) {
                    idList.add(Integer.parseInt(i));
                }
            }
            params.put("projectIdList", idList);
        } else {
            PageUtils pageUtil = new PageUtils(new ArrayList<>(), 0, 0, 0);
            return R.ok().put("data", pageUtil);
        }
        //查询列表数据
        Query query = new Query(params);





        List<ModuleEntity> moduleList = moduleService.queryList(query);
        int total = moduleService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(moduleList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", pageUtil);
    }


    @Autowired
    private ApiService apiService;

    /**
     * 列表
     */
    @RequestMapping("/web/api/list")
    public R apiList(@RequestParam Map<String, Object> params){
        String projectIdList = params.get("moduleIdList") == null ? null : params.get("moduleIdList").toString();
        if (StringUtils.isNotBlank(projectIdList)) {
            String[] arr = projectIdList.split(",");
            List<Integer> idList = new ArrayList<>();
            for (String i : arr) {
                if (StringUtils.isNotBlank(i)) {
                    idList.add(Integer.parseInt(i));
                }
            }
            params.put("moduleIdList", idList);
        } else {
            PageUtils pageUtil = new PageUtils(new ArrayList<>(), 0, 0, 0);
            return R.ok().put("data", pageUtil);
        }

        //查询列表数据
        Query query = new Query(params);

        List<ApiEntity> apiList = apiService.queryList(query);
        int total = apiService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(apiList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", pageUtil);
    }

    @Autowired
    private RequestService requestService;

    /**
     * 列表
     */
    @RequestMapping("/web/request/list")
    public R requestList(@RequestParam Map<String, Object> params){
        String projectIdList = params.get("apiIdList") == null ? null : params.get("apiIdList").toString();
        if (StringUtils.isNotBlank(projectIdList)) {
            String[] arr = projectIdList.split(",");
            List<Integer> idList = new ArrayList<>();
            for (String i : arr) {
                if (StringUtils.isNotBlank(i)) {
                    idList.add(Integer.parseInt(i));
                }
            }
            params.put("apiIdList", idList);
        } else {
            PageUtils pageUtil = new PageUtils(new ArrayList<>(), 0, 0, 0);
            return R.ok().put("data", pageUtil);
        }


        //查询列表数据
        Query query = new Query(params);

        List<RequestEntity> requestList = requestService.queryList(query);
        int total = requestService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(requestList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", pageUtil);
    }

    @Autowired
    private ResponseService responseService;

    /**
     * 列表
     */
    @RequestMapping("/web/response/list")
    public R responseList(@RequestParam Map<String, Object> params){
        String apiIdList = params.get("apiIdList") == null ? null : params.get("apiIdList").toString();
        if (StringUtils.isNotBlank(apiIdList)) {
            String[] arr = apiIdList.split(",");
            List<Integer> idList = new ArrayList<>();
            for (String i : arr) {
                if (StringUtils.isNotBlank(i)) {
                    idList.add(Integer.parseInt(i));
                }
            }
            params.put("apiIdList", idList);
        } else {
            PageUtils pageUtil = new PageUtils(new ArrayList<>(), 0, 0, 0);
            return R.ok().put("data", pageUtil);
        }

        //查询列表数据
        Query query = new Query(params);

        List<ResponseEntity> responseList = responseService.queryList(query);
        int total = responseService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(responseList, total, query.getLimit(), query.getPage());

        return R.ok().put("data", pageUtil);
    }


    @RequestMapping("/web/gen/api")
    public void apiGen(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> params) throws IOException{

        String apiIdList = params.get("apiIdList") == null ? null : params.get("apiIdList").toString();
        List<Integer> idList;
        if (StringUtils.isNotBlank(apiIdList)) {
            String[] arr = apiIdList.split(",");
            idList = new ArrayList<>();
            for (String i : arr) {
                if (StringUtils.isNotBlank(i)) {
                    idList.add(Integer.parseInt(i));
                }
            }
            params.put("apiIdList", idList);
        } else {
            return ;
        }


        byte[] data = sysGeneratorService.apiGeneratorCode(idList);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generator.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

}
