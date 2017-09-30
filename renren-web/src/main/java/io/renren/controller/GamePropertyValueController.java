package io.renren.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.GamePropertyValueEntity;
import io.renren.service.GamePropertyValueService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 属性值表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
@RestController
@RequestMapping("gamepropertyvalue")
public class GamePropertyValueController {
	@Autowired
	private GamePropertyValueService gamePropertyValueService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("gamepropertyvalue:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GamePropertyValueEntity> gamePropertyValueList = gamePropertyValueService.queryList(query);
		int total = gamePropertyValueService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(gamePropertyValueList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("gamepropertyvalue:info")
	public R info(@PathVariable("id") Long id){
		GamePropertyValueEntity gamePropertyValue = gamePropertyValueService.queryObject(id);
		
		return R.ok().put("gamePropertyValue", gamePropertyValue);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("gamepropertyvalue:save")
	public R save(@RequestBody GamePropertyValueEntity gamePropertyValue){
		gamePropertyValueService.save(gamePropertyValue);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("gamepropertyvalue:update")
	public R update(@RequestBody GamePropertyValueEntity gamePropertyValue){
		gamePropertyValueService.update(gamePropertyValue);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("gamepropertyvalue:delete")
	public R delete(@RequestBody Long[] ids){
		gamePropertyValueService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
