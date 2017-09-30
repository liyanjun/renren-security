package io.renren.service;

import io.renren.entity.GamePriceEntity;

import java.util.List;
import java.util.Map;

/**
 * 游戏价格
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GamePriceService {
	
	GamePriceEntity queryObject(Long id);
	
	List<GamePriceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GamePriceEntity gamePrice);
	
	void update(GamePriceEntity gamePrice);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
