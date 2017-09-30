package io.renren.task;

import io.renren.dao.GameOrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("task")
public class BackUp {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GameOrderDao gameOrderDao;

	@Transactional
	public void backup(){
		logger.info("开始订单备份" );
		gameOrderDao.lock();
		gameOrderDao.backup();
		gameOrderDao.deleteHandle();
		logger.info("订单备份结束" );
	}
	
	public void delete(){
		logger.info("开始订单备份删除");
		gameOrderDao.deleteBackup();
		logger.info("结束订单备份删除");
	}
}
