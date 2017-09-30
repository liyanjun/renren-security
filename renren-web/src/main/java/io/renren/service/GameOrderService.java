package io.renren.service;

import com.alibaba.fastjson.JSONObject;
import io.renren.entity.GameOrderEntity;
import io.renren.entity.GamePropertyValueEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GameOrderService {
	
	GameOrderEntity queryObject(Long id);

	List<JSONObject> queryList(Map<String, Object> map);

	List<GameOrderEntity> queryListForBackup(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);
	
	void save(GameOrderEntity gameOrder, List<GamePropertyValueEntity> valueList);
	
	void update(GameOrderEntity gameOrder);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	void handleBatch(Long[] ids);
}
