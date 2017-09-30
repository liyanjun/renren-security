package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.dao.GamePayImgDao;
import io.renren.entity.GamePayImgEntity;
import io.renren.service.GamePayImgService;



@Service("gamePayImgService")
public class GamePayImgServiceImpl implements GamePayImgService {
	@Autowired
	private GamePayImgDao gamePayImgDao;
	
	@Override
	public GamePayImgEntity queryObject(Long id){
		return gamePayImgDao.queryObject(id);
	}
	
	@Override
	public List<GamePayImgEntity> queryList(Map<String, Object> map){
		return gamePayImgDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return gamePayImgDao.queryTotal(map);
	}
	
	@Override
	public void save(GamePayImgEntity gamePayImg){
		gamePayImgDao.save(gamePayImg);
	}
	
	@Override
	public void update(GamePayImgEntity gamePayImg){
		gamePayImgDao.update(gamePayImg);
	}
	
	@Override
	public void delete(Long id){
		gamePayImgDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		gamePayImgDao.deleteBatch(ids);
	}

	@Override
	public List<GamePayImgEntity> queryByMatchPrice(BigDecimal totalAmount, int type) {
		Map map = new HashMap();
		map.put("totalAmount",totalAmount);
		map.put("type",type);
		return gamePayImgDao.queryByMatchPrice(map);
	}

}
