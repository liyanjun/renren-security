package io.renren.controller;

import java.util.List;
import java.util.Map;

import io.renren.validator.ValidatorUtils;
import io.renren.validator.group.AddGroup;
import io.renren.validator.group.UpdateGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.GamePriceEntity;
import io.renren.service.GamePriceService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 游戏价格
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
@RestController
@RequestMapping("gameprice")
public class GamePriceController {
	@Autowired
	private GamePriceService gamePriceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("gameprice:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GamePriceEntity> gamePriceList = gamePriceService.queryList(query);
		int total = gamePriceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(gamePriceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("gameprice:info")
	public R info(@PathVariable("id") Long id){
		GamePriceEntity gamePrice = gamePriceService.queryObject(id);
		
		return R.ok().put("gamePrice", gamePrice);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("gameprice:save")
	public R save(@RequestBody GamePriceEntity gamePrice){
		ValidatorUtils.validateEntity(gamePrice,AddGroup.class);
		gamePriceService.save(gamePrice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("gameprice:update")
	public R update(@RequestBody GamePriceEntity gamePrice){
		ValidatorUtils.validateEntity(gamePrice,UpdateGroup.class);
		gamePriceService.update(gamePrice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("gameprice:delete")
	public R delete(@RequestBody Long[] ids){
		gamePriceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
