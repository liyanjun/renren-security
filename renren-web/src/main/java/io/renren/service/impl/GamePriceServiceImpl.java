package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.GamePriceDao;
import io.renren.entity.GamePriceEntity;
import io.renren.service.GamePriceService;



@Service("gamePriceService")
public class GamePriceServiceImpl implements GamePriceService {
	@Autowired
	private GamePriceDao gamePriceDao;
	
	@Override
	public GamePriceEntity queryObject(Long id){
		return gamePriceDao.queryObject(id);
	}
	
	@Override
	public List<GamePriceEntity> queryList(Map<String, Object> map){
		return gamePriceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return gamePriceDao.queryTotal(map);
	}
	
	@Override
	public void save(GamePriceEntity gamePrice){
		gamePriceDao.save(gamePrice);
	}
	
	@Override
	public void update(GamePriceEntity gamePrice){
		gamePriceDao.update(gamePrice);
	}
	
	@Override
	public void delete(Long id){
		gamePriceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		gamePriceDao.deleteBatch(ids);
	}
	
}
