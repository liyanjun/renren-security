package io.renren.dao;

import io.renren.entity.GamePropertyValueEntity;

import java.util.List;

/**
 * 属性值表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GamePropertyValueDao extends BaseDao<GamePropertyValueEntity> {

    List<GamePropertyValueEntity> findByOrderId(Long id);
}
