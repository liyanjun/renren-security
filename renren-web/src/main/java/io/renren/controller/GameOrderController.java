package io.renren.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.renren.dao.GamePropertyDao;
import io.renren.entity.GamePropertyEntity;
import io.renren.service.GamePropertyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.GameOrderEntity;
import io.renren.service.GameOrderService;
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
@RequestMapping("gameorder")
public class GameOrderController {
	@Autowired
	private GameOrderService gameOrderService;

	@Autowired
	private GamePropertyService gamePropertyService;

	/**
	 * 列表
	 */
	@RequestMapping("/colModel")
	@RequiresPermissions("gameorder:list")
	public List<JSONObject> colModel(){
		List<GamePropertyEntity> gamePropertyEntities = gamePropertyService.queryAll();
		List<JSONObject> gameOrderCol = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();jsonObject.put("label","id");jsonObject.put("name","id");jsonObject.put("index","id");jsonObject.put("width","50");jsonObject.put("key",true);
		JSONObject jsonObject2 = new JSONObject();jsonObject2.put("label","游戏名");jsonObject2.put("name","name");jsonObject2.put("index","name");jsonObject2.put("width","80");
		JSONObject jsonObject3 = new JSONObject();jsonObject3.put("label","游戏账号");jsonObject3.put("name","account");jsonObject3.put("index","account");jsonObject3.put("width","80");
		JSONObject jsonObject4 = new JSONObject();jsonObject4.put("label","是否处理");jsonObject4.put("name","isHandle");jsonObject4.put("index","isHandle");jsonObject4.put("width","80");
		JSONObject jsonObject5 = new JSONObject();jsonObject5.put("label","订单总额");jsonObject5.put("name","totalAmount");jsonObject5.put("index","totalAmount");jsonObject5.put("width","80");
		JSONObject jsonObject6 = new JSONObject();jsonObject6.put("label","购买数量");jsonObject6.put("name","gamePriceNumber");jsonObject6.put("width","80");
		JSONObject jsonObject7 = new JSONObject();jsonObject7.put("label","购买面额");jsonObject7.put("name","gamePrice");jsonObject7.put("width","80");
		JSONObject jsonObject8 = new JSONObject();jsonObject8.put("label","是否支付");jsonObject8.put("name","isPay");jsonObject8.put("width","80");

		gameOrderCol.add(jsonObject);
		gameOrderCol.add(jsonObject2);
		gameOrderCol.add(jsonObject3);
		for (GamePropertyEntity gameProperty: gamePropertyEntities) {
			JSONObject jo = new JSONObject();
			jo.put("label",gameProperty.getLabel());jo.put("name",gameProperty.getName());jo.put("width","80");
			gameOrderCol.add(jo);
		}
		gameOrderCol.add(jsonObject6);
		gameOrderCol.add(jsonObject7);
		gameOrderCol.add(jsonObject5);
		gameOrderCol.add(jsonObject4);
		gameOrderCol.add(jsonObject8);
		return gameOrderCol;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("gameorder:list")
	public R list(@RequestParam Map<String, Object> params) throws UnsupportedEncodingException {
		String gameName = (String) params.get("gameName");
		if(org.apache.commons.lang.StringUtils.isNotBlank(gameName)){
			gameName = new String(gameName.getBytes("iso8859-1"),"UTF-8");
			params.put("gameName",gameName);
		}

		//查询列表数据
        Query query = new Query(params);

		List<JSONObject> gameOrderList = gameOrderService.queryList(query);
		int total = gameOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(gameOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("gameorder:info")
	public R info(@PathVariable("id") Long id){
		GameOrderEntity gameOrder = gameOrderService.queryObject(id);
		
		return R.ok().put("gameOrder", gameOrder);
	}
	
	/**
	 * 保存
	 *//*
	@RequestMapping("/save")
	@RequiresPermissions("gameorder:save")
	public R save(@RequestBody GameOrderEntity gameOrder){
		gameOrderService.save(gameOrder, valueList);
		
		return R.ok();
	}*/
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("gameorder:update")
	public R update(@RequestBody GameOrderEntity gameOrder){
		gameOrderService.update(gameOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改订单为已处理
	 */
	@RequestMapping("/handle")
	@RequiresPermissions("gameorder:update")
	public R handle(@RequestBody Long[] ids){
		gameOrderService.handleBatch(ids);
		
		return R.ok();
	}
	
}
