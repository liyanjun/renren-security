package io.renren.service;

import io.renren.entity.GamePropertyEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GamePropertyService {
	
	GamePropertyEntity queryObject(Long id);
	
	List<GamePropertyEntity> queryList(Map<String, Object> map);

	List<GamePropertyEntity> queryAll();

	int queryTotal(Map<String, Object> map);
	
	void save(GamePropertyEntity gameProperty);
	
	void update(GamePropertyEntity gameProperty);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
