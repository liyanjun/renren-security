package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.GamePagePropertyDao;
import io.renren.entity.GamePagePropertyEntity;
import io.renren.service.GamePagePropertyService;



@Service("gamePagePropertyService")
public class GamePagePropertyServiceImpl implements GamePagePropertyService {
	@Autowired
	private GamePagePropertyDao gamePagePropertyDao;
	
	@Override
	public GamePagePropertyEntity queryObject(Long id){
		return gamePagePropertyDao.queryObject(id);
	}
	
	@Override
	public List<GamePagePropertyEntity> queryList(Map<String, Object> map){
		return gamePagePropertyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return gamePagePropertyDao.queryTotal(map);
	}
	
	@Override
	public void save(GamePagePropertyEntity gamePageProperty){
		gamePagePropertyDao.save(gamePageProperty);
	}
	
	@Override
	public void update(GamePagePropertyEntity gamePageProperty){
		gamePagePropertyDao.update(gamePageProperty);
	}
	
	@Override
	public void delete(Long id){
		gamePagePropertyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		gamePagePropertyDao.deleteBatch(ids);
	}
	
}
