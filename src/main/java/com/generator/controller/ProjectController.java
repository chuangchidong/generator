package com.generator.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generator.entity.ProjectEntity;
import com.generator.service.ProjectService;
import com.generator.utils.PageUtils;
import com.generator.utils.Query;
import com.generator.utils.R;




/**
 * 项目信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
@RestController
@RequestMapping("/free/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("free:project:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ProjectEntity> projectList = projectService.queryList(query);
		int total = projectService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(projectList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("free:project:info")
	public R info(@PathVariable("id") Integer id){
		ProjectEntity project = projectService.queryObject(id);
		
		return R.ok().put("project", project);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("free:project:save")
	public R save(@RequestBody ProjectEntity project){
		projectService.save(project);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("free:project:update")
	public R update(@RequestBody ProjectEntity project){
		projectService.update(project);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("free:project:delete")
	public R delete(@RequestBody Integer[] ids){
		projectService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
