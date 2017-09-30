package io.renren.dao;

import io.renren.entity.GameOrderEntity;

import java.util.List;

/**
 * 订单表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GameOrderDao extends BaseDao<GameOrderEntity> {

    Long insert(GameOrderEntity gameOrder);

    void handleBatch(Long[] ids);

    List<GameOrderEntity> lock();

    void backup();

    void deleteHandle();

    void deleteBackup();

}
