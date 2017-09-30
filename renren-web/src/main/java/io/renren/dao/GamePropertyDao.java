package io.renren.dao;

import io.renren.entity.GamePropertyEntity;

import java.util.List;

/**
 * 属性表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GamePropertyDao extends BaseDao<GamePropertyEntity> {

    List<GamePropertyEntity> queryAll();
}
