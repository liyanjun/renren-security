package io.renren.service;

import io.renren.entity.GamePagePropertyEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GamePagePropertyService {
	
	GamePagePropertyEntity queryObject(Long id);
	
	List<GamePagePropertyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GamePagePropertyEntity gamePageProperty);
	
	void update(GamePagePropertyEntity gamePageProperty);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
