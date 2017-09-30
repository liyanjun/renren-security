package io.renren.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.renren.entity.SysOssEntity;
import io.renren.oss.OSSFactory;
import io.renren.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.GamePayImgEntity;
import io.renren.service.GamePayImgService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * 支付图片截图
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
@RestController
@RequestMapping("gamepayimg")
public class GamePayImgController {
	@Autowired
	private GamePayImgService gamePayImgService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("gamepayimg:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<GamePayImgEntity> gamePayImgList = gamePayImgService.queryList(query);
		int total = gamePayImgService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(gamePayImgList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("gamepayimg:info")
	public R info(@PathVariable("id") Long id){
		GamePayImgEntity gamePayImg = gamePayImgService.queryObject(id);
		
		return R.ok().put("gamePayImg", gamePayImg);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("gamepayimg:save")
	public R save(@RequestBody GamePayImgEntity gamePayImg){
		gamePayImgService.save(gamePayImg);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("gamepayimg:update")
	public R update(@RequestBody GamePayImgEntity gamePayImg){
		gamePayImg.setUpdateId(ShiroUtils.getUserId());
		gamePayImgService.update(gamePayImg);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("gamepayimg:delete")
	public R delete(@RequestBody Long[] ids){
		gamePayImgService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		//上传文件
		String url = OSSFactory.build().upload(file.getBytes());

		//保存文件信息
		GamePayImgEntity gamePayImgEntity = new GamePayImgEntity();
		gamePayImgEntity.setUrl(url);
		gamePayImgEntity.setCreatorId(ShiroUtils.getUserId());
//		gamePayImgEntity.setMatchPrice(BigDecimal.valueOf(Double.valueOf(matchPrice)));

//		String userAgent = request.getHeader("user-agent").toLowerCase();
//		if(userAgent.indexOf("micromessenger")>-1){//微信客户端
//			gamePayImgEntity.setType(1);
//		}else{
//			gamePayImgEntity.setType(2);
//		}
		gamePayImgService.save(gamePayImgEntity);

		return R.ok().put("url", url);
	}
	
}
