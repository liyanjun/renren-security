package io.renren.service;

import io.renren.entity.GamePayImgEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 支付图片截图
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GamePayImgService {
	
	GamePayImgEntity queryObject(Long id);
	
	List<GamePayImgEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GamePayImgEntity gamePayImg);
	
	void update(GamePayImgEntity gamePayImg);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<GamePayImgEntity> queryByMatchPrice(BigDecimal totalAmount, int type);
}
