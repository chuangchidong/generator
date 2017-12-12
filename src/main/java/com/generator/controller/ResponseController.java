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

import com.generator.entity.ResponseEntity;
import com.generator.service.ResponseService;
import com.generator.utils.PageUtils;
import com.generator.utils.Query;
import com.generator.utils.R;




/**
 * 返回结果字段信息
 * 
 * @author zhangzhidong
 * @email 1206214477@qq.com
 * @date 2017-12-06 16:23:14
 */
@RestController
@RequestMapping("/free/response")
public class ResponseController {
	@Autowired
	private ResponseService responseService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("free:response:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ResponseEntity> responseList = responseService.queryList(query);
		int total = responseService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(responseList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("free:response:info")
	public R info(@PathVariable("id") Integer id){
		ResponseEntity response = responseService.queryObject(id);
		
		return R.ok().put("response", response);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("free:response:save")
	public R save(@RequestBody ResponseEntity response){
		responseService.save(response);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("free:response:update")
	public R update(@RequestBody ResponseEntity response){
		responseService.update(response);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("free:response:delete")
	public R delete(@RequestBody Integer[] ids){
		responseService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
