package io.renren.service;

import io.renren.entity.GamePropertyValueEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性值表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GamePropertyValueService {
	
	GamePropertyValueEntity queryObject(Long id);
	
	List<GamePropertyValueEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GamePropertyValueEntity gamePropertyValue);
	
	void update(GamePropertyValueEntity gamePropertyValue);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
