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

import com.generator.entity.ModuleEntity;
import com.generator.service.ModuleService;
import com.generator.utils.PageUtils;
import com.generator.utils.Query;
import com.generator.utils.R;


/**
 * 模块信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
@RestController
@RequestMapping("/free/module")
public class ModuleController {
	@Autowired
	private ModuleService moduleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("free:module:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ModuleEntity> moduleList = moduleService.queryList(query);
		int total = moduleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(moduleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("free:module:info")
	public R info(@PathVariable("id") Integer id){
		ModuleEntity module = moduleService.queryObject(id);
		
		return R.ok().put("module", module);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("free:module:save")
	public R save(@RequestBody ModuleEntity module){
		moduleService.save(module);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("free:module:update")
	public R update(@RequestBody ModuleEntity module){
		moduleService.update(module);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("free:module:delete")
	public R delete(@RequestBody Integer[] ids){
		moduleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
