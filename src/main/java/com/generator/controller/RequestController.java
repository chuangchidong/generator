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

import com.generator.entity.RequestEntity;
import com.generator.service.RequestService;
import com.generator.utils.PageUtils;
import com.generator.utils.Query;
import com.generator.utils.R;




/**
 * 请求参数信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
@RestController
@RequestMapping("/free/request")
public class RequestController {
	@Autowired
	private RequestService requestService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("free:request:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RequestEntity> requestList = requestService.queryList(query);
		int total = requestService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(requestList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("free:request:info")
	public R info(@PathVariable("id") Integer id){
		RequestEntity request = requestService.queryObject(id);
		
		return R.ok().put("request", request);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("free:request:save")
	public R save(@RequestBody RequestEntity request){
		requestService.save(request);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("free:request:update")
	public R update(@RequestBody RequestEntity request){
		requestService.update(request);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("free:request:delete")
	public R delete(@RequestBody Integer[] ids){
		requestService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
