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

import com.generator.entity.ApiEntity;
import com.generator.service.ApiService;
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
@RequestMapping("/free/api")
public class ApiController {
	@Autowired
	private ApiService apiService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("free:api:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ApiEntity> apiList = apiService.queryList(query);
		int total = apiService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(apiList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("free:api:info")
	public R info(@PathVariable("id") Integer id){
		ApiEntity api = apiService.queryObject(id);
		
		return R.ok().put("api", api);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("free:api:save")
	public R save(@RequestBody ApiEntity api){
		apiService.save(api);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("free:api:update")
	public R update(@RequestBody ApiEntity api){
		apiService.update(api);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("free:api:delete")
	public R delete(@RequestBody Integer[] ids){
		apiService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
