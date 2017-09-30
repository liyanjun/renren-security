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

import io.renren.entity.GamePagePropertyEntity;
import io.renren.service.GamePagePropertyService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 订单表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
@RestController
@RequestMapping("gamepageproperty")
public class GamePagePropertyController {
	@Autowired
	private GamePagePropertyService gamePagePropertyService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("gamepageproperty:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GamePagePropertyEntity> gamePagePropertyList = gamePagePropertyService.queryList(query);
		int total = gamePagePropertyService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(gamePagePropertyList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("gamepageproperty:info")
	public R info(@PathVariable("id") Long id){
		GamePagePropertyEntity gamePageProperty = gamePagePropertyService.queryObject(id);
		
		return R.ok().put("gamePageProperty", gamePageProperty);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("gamepageproperty:save")
	public R save(@RequestBody GamePagePropertyEntity gamePageProperty){
		gamePagePropertyService.save(gamePageProperty);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("gamepageproperty:update")
	public R update(@RequestBody GamePagePropertyEntity gamePageProperty){
		gamePagePropertyService.update(gamePageProperty);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("gamepageproperty:delete")
	public R delete(@RequestBody Long[] ids){
		gamePagePropertyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
