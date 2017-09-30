package io.renren.service.impl;

import io.renren.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.GamePropertyDao;
import io.renren.entity.GamePropertyEntity;
import io.renren.service.GamePropertyService;



@Service("gamePropertyService")
public class GamePropertyServiceImpl implements GamePropertyService {
	@Autowired
	private GamePropertyDao gamePropertyDao;
	
	@Override
	public GamePropertyEntity queryObject(Long id){
		return gamePropertyDao.queryObject(id);
	}
	
	@Override
	public List<GamePropertyEntity> queryList(Map<String, Object> map){
		return gamePropertyDao.queryList(map);
	}

	@Override
	public List<GamePropertyEntity> queryAll(){
		return gamePropertyDao.queryAll();
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return gamePropertyDao.queryTotal(map);
	}
	
	@Override
	public void save(GamePropertyEntity gameProperty){
		gamePropertyDao.save(gameProperty);
	}
	
	@Override
	public void update(GamePropertyEntity gameProperty){
		gamePropertyDao.update(gameProperty);
	}
	
	@Override
	public void delete(Long id){
		gamePropertyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		gamePropertyDao.deleteBatch(ids);
	}
	
}
