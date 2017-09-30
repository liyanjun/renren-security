package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.GamePropertyValueDao;
import io.renren.entity.GamePropertyValueEntity;
import io.renren.service.GamePropertyValueService;



@Service("gamePropertyValueService")
public class GamePropertyValueServiceImpl implements GamePropertyValueService {
	@Autowired
	private GamePropertyValueDao gamePropertyValueDao;
	
	@Override
	public GamePropertyValueEntity queryObject(Long id){
		return gamePropertyValueDao.queryObject(id);
	}
	
	@Override
	public List<GamePropertyValueEntity> queryList(Map<String, Object> map){
		return gamePropertyValueDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return gamePropertyValueDao.queryTotal(map);
	}
	
	@Override
	public void save(GamePropertyValueEntity gamePropertyValue){
		gamePropertyValueDao.save(gamePropertyValue);
	}
	
	@Override
	public void update(GamePropertyValueEntity gamePropertyValue){
		gamePropertyValueDao.update(gamePropertyValue);
	}
	
	@Override
	public void delete(Long id){
		gamePropertyValueDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		gamePropertyValueDao.deleteBatch(ids);
	}
	
}
